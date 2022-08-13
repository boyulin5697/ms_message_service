package com.by.ms.message.service.gateway.mutation;


import com.by.commons.communication.StandardResp;
import com.by.ms.message.service.kernel.requests.SendEmailRequest;
import com.by.ms.message.service.kernel.requests.SendSMSRequest;
import com.by.ms.message.service.kernel.service.MailService;
import com.by.ms.message.service.kernel.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class MessageMutationGateway {

    @Autowired
    private MailService mailService;

    @Autowired
    private SMSService smsService;

    /**
     * Request for sending email code
     * @param emailRequest
     * @return
     */
    @RequestMapping("/getEmailCode")
    public StandardResp getEmail(@RequestBody SendEmailRequest emailRequest){
        try{
            return mailService.sendCodeMail(emailRequest);
        }catch (Exception e){
            return new StandardResp().error("Request register email code failed, due to internal error!");
        }
    }

    /**
     * Request for sending sms code
     * @param smsRequest
     * @return
     */
    @RequestMapping("/getSMSCode")
    public StandardResp getSMS(@RequestBody SendSMSRequest smsRequest){
        try{
            return smsService.sendCodeSMS(smsRequest);
        }catch (Exception e){
            return new StandardResp().error("Request register email code failed, due to internal error!");
        }
    }

}
