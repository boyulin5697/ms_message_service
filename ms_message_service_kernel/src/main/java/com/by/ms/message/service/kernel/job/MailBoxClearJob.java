package com.by.ms.message.service.kernel.job;

import com.by.ms.message.service.api.consts.InnerMessageTypes;
import com.by.ms.message.service.kernel.consts.MessageType;
import com.by.ms.message.service.kernel.dao.MailboxDao;
import com.by.ms.message.service.kernel.entities.MailBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Mailbox Clear Job
 *
 * @author by.
 * @date 2022/8/13
 */
@Component
@Slf4j
public class MailBoxClearJob {

    @Autowired
    private MailboxDao mailboxDao;

    @Scheduled(cron = "0 0 0 ? * MON")
    public void mailBoxClearJob(){
        log.warn("=== Starting the inner information mailbox clean up process ===");
        List<MailBox> mailBoxList = mailboxDao.getAllData();
        Date date = new Date();
        if(mailBoxList.size()>1000){
            //Count down counter to get the time to stop the thread pools.
            CountDownLatch latch = new CountDownLatch(mailBoxList.size());
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            for(int i=0;i<mailBoxList.size()-1;i++){
                int boxNum = i;
                executorService.submit(()->{
                    try {
                        MailBox mailBox = mailBoxList.get(boxNum);
                        mailBox.setMessageQueue(clearOutdateData(date, mailBox.getMessageQueue()));
                        mailboxDao.save(mailBox);
                    }catch (Exception e){
                        log.error("There is an error occured with clearing mailbox!",e);
                    }finally {
                        latch.countDown();
                    }
                });
            }
            try {
                latch.await();
            }catch (InterruptedException e){
                log.info("Mailbox check finished!",e);
            }
            executorService.shutdown();

        }else{
            mailBoxList.forEach(mailBox -> {
                mailBox.setMessageQueue(clearOutdateData(date, mailBox.getMessageQueue()));
                mailboxDao.save(mailBox);
            });
        }
        log.warn(" === Inner message clean up ended ===");
    }


    public List<MailBox.MessageInfo> clearOutdateData(Date date, List<MailBox.MessageInfo> mailBoxDeque){
        List<MailBox.MessageInfo> afterClearDeque = new LinkedList<>();
        mailBoxDeque.forEach(message -> {
            if((date.getTime() - message.getSendTime().getTime())<604800000){
                if(message.getMessageType()!= InnerMessageTypes.FRIEND_INVITATION) {
                    afterClearDeque.add(message);
                }
            }
        });
        return afterClearDeque;
    }
}
