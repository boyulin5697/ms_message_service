package com.by.message.service.gateway.controllers;


import com.by.commons.communication.StandardResp;
import com.by.ms.message.service.api.SendEmailRequest;
import com.by.ms.message.service.kernel.mail.service.MailService;
import com.by.ms.message.service.kernel.mq.MessageDeliveryType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message Controller
 *
 * @author by.
 * @date 2022/4/28
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MailService mailService;

    @RequestMapping("/getRegisterEmail")
    public StandardResp getRegisterEmail(@ModelAttribute SendEmailRequest emailRequest){
        try{
            return mailService.sendMail(emailRequest, MessageDeliveryType.REGISTER);
        }catch (Exception e){
            return new StandardResp().error("Request register email code failed, due to internal error!");
        }
    }
}
