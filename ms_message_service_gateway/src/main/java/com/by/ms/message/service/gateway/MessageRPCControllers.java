package com.by.ms.message.service.gateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.by.commons.communication.StandardResp;
import com.by.ms.message.apis.MessageApis;
import com.by.ms.message.service.api.consts.StatusCode;
import com.by.ms.message.service.api.requests.SendInnerMessageRequest;
import com.by.ms.message.service.api.requests.SendMessageRequest;
import com.by.ms.message.service.api.responses.SendMessageResponse;
import com.by.ms.message.service.kernel.consts.MessageDeliveryType;
import com.by.ms.message.service.kernel.entities.MailBox;
import com.by.ms.message.service.kernel.requests.SendEmailRequest;
import com.by.ms.message.service.kernel.requests.SendSMSRequest;
import com.by.ms.message.service.kernel.service.InnerMessageService;
import com.by.ms.message.service.kernel.service.MailService;
import com.by.ms.message.service.kernel.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message Apis
 *
 * @author by.
 * @date 2022/6/1
 */
@Slf4j
@RestController
public class MessageRPCControllers implements MessageApis {

    @Autowired
    private MailService mailService;

    @Autowired
    private SMSService smsService;

    @Autowired
    private InnerMessageService innerMessageService;

    @Override
    public SendMessageResponse<String> requestRegisterEmail(@RequestBody SendMessageRequest request) {
        SendMessageResponse<String> response = new SendMessageResponse<>();
        if(request.getDestination()==null){
            response.setStatus(StatusCode.FAILED);
            response.setData("Email is required!");
            return response;
        }
        try{
            SendEmailRequest emailRequest = new SendEmailRequest();
            emailRequest.setEmail(request.getDestination());
            emailRequest.setDeliveryType(MessageDeliveryType.REGISTER);
            StandardResp<String> resp = mailService.sendCodeMail(emailRequest);
            if(resp.getCode()!=200){
                response.setStatus(StatusCode.FAILED);
                response.setData(resp.getMessage());
                return response;
            }
            String code = resp.getData();
            response.setStatus(StatusCode.SUCCESS);
            response.setData(code);
        }catch (Exception e){
            log.error("openFeign RPC occurs error!",e);
            response.setData("");
            response.setStatus(StatusCode.FAILED);
        }
        return response;
    }

    @Override
    public SendMessageResponse<String> requestRegisterSms(@RequestBody SendMessageRequest request) {
        SendMessageResponse<String> response = new SendMessageResponse<>();
        if(request.getDestination()==null){
            response.setStatus(StatusCode.FAILED);
            response.setData("telephone number is required!");
            return response;
        }
        try{
            SendSMSRequest smsRequest = new SendSMSRequest();
            smsRequest.setTelephone(request.getDestination());
            smsRequest.setDeliveryType(MessageDeliveryType.REGISTER);
            StandardResp<String> resp = smsService.sendCodeSMS(smsRequest);
            if(resp.getCode()!=200){
                response.setStatus(StatusCode.FAILED);
                response.setData(resp.getMessage());
                return response;
            }
            String code = resp.getData();
            response.setStatus(StatusCode.SUCCESS);
            response.setData(code);
        }catch (Exception e){
            log.error("openFeign RPC occurs error!",e);
            response.setData("");
            response.setStatus(StatusCode.FAILED);
        }
        return response;
    }

    @Override
    public SendMessageResponse<String> sendInnerMessage(SendInnerMessageRequest request) {
        SendMessageResponse<String> response = new SendMessageResponse<>();
        try{
            innerMessageService.sendInnerMessage(request);
            response.setStatus(StatusCode.SUCCESS);
            response.setData("Succeed!");
        }catch (Exception e){
            log.error("openFeign RPC occurs error!",e);
            response.setStatus(StatusCode.FAILED);
            response.setData("Failed!");
        }
        return response;
    }
}
