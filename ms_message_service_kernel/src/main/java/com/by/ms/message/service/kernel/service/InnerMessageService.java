package com.by.ms.message.service.kernel.service;

import com.by.commons.tools.modelmappers.ModelMapperUtils;
import com.by.ms.message.service.api.requests.SendInnerMessageRequest;
import com.by.ms.message.service.kernel.dao.InnerMessageDao;
import com.by.ms.message.service.kernel.dao.MailboxDao;
import com.by.ms.message.service.kernel.entities.InnerMessage;
import com.by.ms.message.service.kernel.entities.MailBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void sendInnerMessage(SendInnerMessageRequest request){
        InnerMessage message = new InnerMessage();
        ModelMapperUtils.map(request,message);
        MailBox mailBox;
        if(null==mailboxDao.findById(request.getDestId())){
            mailBox = new MailBox();
        }else{
            mailBox = mailboxDao.findById(request.getDestId());
        }
        mailBox.getMessageQueue().push(message.getMessageId());
        mailboxDao.save(mailBox);
        innerMessageDao.save(message);
    }



}
