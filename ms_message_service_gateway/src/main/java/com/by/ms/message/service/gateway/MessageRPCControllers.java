package com.by.ms.message.service.gateway;

import com.by.commons.communication.StandardResp;
import com.by.ms.message.service.api.apis.MessageApis;
import com.by.ms.message.service.api.consts.StatusCode;
import com.by.ms.message.service.api.requests.SendMessageRequest;
import com.by.ms.message.service.api.responses.SendMessageResponse;
import com.by.ms.message.service.kernel.consts.MessageDeliveryType;
import com.by.ms.message.service.kernel.requests.SendEmailRequest;
import com.by.ms.message.service.kernel.requests.SendSMSRequest;
import com.by.ms.message.service.kernel.service.MailService;
import com.by.ms.message.service.kernel.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message Apis
 *
 * @author by.
 * @date 2022/6/1
 */
@RestController
public class MessageRPCControllers implements MessageApis {

    @Autowired
    private MailService mailService;

    @Autowired
    private SMSService smsService;

    @Override
    public SendMessageResponse<String> requestRegisterEmail(SendMessageRequest request) {
        SendMessageResponse<String> response = new SendMessageResponse<>();
        try{
            SendEmailRequest emailRequest = new SendEmailRequest();
            emailRequest.setEmail(request.getDestination());
            emailRequest.setDeliveryType(MessageDeliveryType.REGISTER);
            StandardResp<String> resp = mailService.sendCodeMail(emailRequest);
            String code = resp.getData();
            response.setStatus(StatusCode.SUCCESS);
            response.setData(code);
        }catch (Exception e){
            response.setData("");
            response.setStatus(StatusCode.FAILED);
        }
        return response;
    }

    @Override
    public SendMessageResponse<String> requestRegisterSms(SendMessageRequest request) {
        SendMessageResponse<String> response = new SendMessageResponse<>();
        try{
            SendSMSRequest smsRequest = new SendSMSRequest();
            smsRequest.setTelephone(request.getDestination());
            smsRequest.setDeliveryType(MessageDeliveryType.REGISTER);
            StandardResp<String> resp = smsService.sendCodeSMS(smsRequest);
            String code = resp.getData();
            response.setStatus(StatusCode.SUCCESS);
            response.setData(code);
        }catch (Exception e){
            response.setData("");
            response.setStatus(StatusCode.FAILED);
        }
        return response;
    }
}
