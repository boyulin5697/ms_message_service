package com.by.ms.message.service.kernel.service;

import com.by.commons.communication.StandardResp;
import com.by.commons.contexts.ContextLocal;
import com.by.commons.tools.modelmappers.ModelMapperUtils;
import com.by.ms.message.service.api.requests.SendBatchInnerMessageRequest;
import com.by.ms.message.service.api.requests.SendInnerMessageRequest;
import com.by.ms.message.service.kernel.dao.InnerMessageDao;
import com.by.ms.message.service.kernel.dao.MailboxDao;
import com.by.ms.message.service.kernel.entities.InnerMessage;
import com.by.ms.message.service.kernel.entities.MailBox;
import com.by.ms.message.service.kernel.requests.innermessage.QueryMessageContentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Inner Message Service
 *
 * @author by.
 * @date 2022/8/13
 */
@Service
public class InnerMessageService {

    @Autowired
    private InnerMessageDao innerMessageDao;

    @Autowired
    private MailboxDao mailboxDao;

    @Transactional(rollbackFor = Throwable.class)
    public synchronized void sendInnerMessage(SendInnerMessageRequest request){
        InnerMessage message = new InnerMessage();
        ModelMapperUtils.map(request,message);
        List<String>destList = new LinkedList<>();
        destList.add(request.getDestId());
        message.setDestId(destList);
        MailBox mailBox;
        if(null==mailboxDao.findById(request.getDestId())){
            mailBox = new MailBox();
            mailBox.setId(request.getDestId());
        }else{
            mailBox = mailboxDao.findById(request.getDestId());
        }
        mailBox.getMessageQueue().add(MailBox.createMessageInfo(message.getId(),message.getInnerMessageType(),message.getSendTime()));
        mailboxDao.save(mailBox);
        innerMessageDao.save(message);
    }

    @Transactional(rollbackFor = Throwable.class)
    public synchronized void sendBatchInnerMessage(SendBatchInnerMessageRequest request){
        InnerMessage message = new InnerMessage();
        ModelMapperUtils.map(request,message);
        request.getDestId().parallelStream().forEach(dest ->{
            MailBox mailBox;
            if(null==mailboxDao.findById(dest)){
                mailBox = new MailBox();
                mailBox.setId(dest);
            }else{
                mailBox = mailboxDao.findById(dest);
            }
            mailBox.getMessageQueue().add(MailBox.createMessageInfo(message.getId(),message.getInnerMessageType(),message.getSendTime()));
            mailboxDao.save(mailBox);
        });
        innerMessageDao.save(message);
    }


    /**
     * Query the mailbox of a detailed user
     */
    public StandardResp queryMailBox(){
        String userNo = ContextLocal.getContext().getUserNo();
        MailBox mailBox = mailboxDao.findById(userNo);
        return new StandardResp().success(mailBox.getMessageQueue());
    }


    /**
     * Query inner message by id
     */
    public StandardResp queryMessage(QueryMessageContentRequest request){
        return innerMessageDao.findById(request.getMessageId())!=null?new StandardResp<>().success(innerMessageDao.findById(request.getMessageId())):new StandardResp<>().error("The message is not exist!");
    }





}
