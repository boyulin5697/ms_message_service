package com.by.ms.message.service.kernel.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.by.commons.communication.StandardResp;
import com.by.commons.consts.ResponseCodeEnum;
import com.by.commons.mq.RocketmqProducer;
import com.by.commons.tools.GenerateCodeTool;
import com.by.commons.tools.UuidTool;
import com.by.ms.message.service.api.SendSMSRequest;
import com.by.ms.message.service.kernel.SmsLogEntity;
import com.by.ms.message.service.kernel.consts.MessageDeliveryType;
import com.by.ms.message.service.kernel.consts.MessageSendStatus;
import com.by.ms.message.service.kernel.consts.MessageType;
import com.by.ms.message.service.kernel.dao.SMSMapper;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.Access;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * SMS service
 *
 * @author by.
 * @date 2022/4/29
 */
@RefreshScope
@Service
@Slf4j
public class SMSService extends ServiceImpl<SMSMapper, SmsLogEntity> {

    private static final String topic = "topic_message_service";

    @Value(value = "${ms.sms.secretId}")
    private String secretId;

    @Value(value = "${ms.sms.secretKey}")
    private String secretKey;

    @Value(value = "${ms.sms.endPoint}")
    private String endPoint;

    @Value(value = "${ms.sms.region}")
    private String region;

    @Value(value = "${ms.sms.sdkAppId}")
    private String sdkAppId;

    @Value(value = "${ms.sms.signName}")
    private String signName;

    @Value(value = "${ms.sms.registerTemplateId}")
    private String registerTemplate;

    @Value(value = "${ms.sms.loginedTemplateId}")
    private String loginedTemplate;

    @Autowired
    private RocketmqProducer producer;

    @Autowired
    private SMSMapper smsMapper;

    /**
     * In the future, the goal is overloading a new method which have more functions.
     * @param request
     * @return
     */
    public StandardResp sendCodeSMS(SendSMSRequest request){
        try{
            Assert.hasText(request.getTelephone(),"The goal telephone cannot be null!");
        }catch (Exception e){
            return new StandardResp().error(ResponseCodeEnum.REQUEST_ERROR,e.getMessage());
        }
        String code = GenerateCodeTool.generateCode(6);
        SmsLogEntity sms = new SmsLogEntity();
        Map<String,Object>params = new HashMap<>();
        params.put("messageType", MessageType.SMS);
        params.put("messageDeliveryType", request.getDeliveryType());
        params.put("telephone",request.getTelephone());
        params.put("code",code);
        params.put("smsId",sms.getId());
        producer.asyncSend(topic,params);
        try{
            sms.setDeliveryType(request.getDeliveryType());
            sms.setSendTime(new Date());
            sms.setDestNumber(request.getTelephone());
            smsMapper.insert(sms);
        }catch (Exception e){
        log.error("There is something wrong with recording SMS record",e);
    }
        return new StandardResp().success(code,"The SMS has been successfully sent!");

    }

    /**
     * Send Sms verify code
     * @param telephone
     * @param code
     * @param type
     * @throws Exception
     */
    public void send(String telephone, String code, int type) throws Exception{
        Credential cred = new Credential(secretId,secretKey);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(endPoint);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        SmsClient client = new SmsClient(cred,region);
        SendSmsRequest req = new SendSmsRequest();
        String[] phoneNumberSet1 = {telephone};
        req.setPhoneNumberSet(phoneNumberSet1);
        req.setSmsSdkAppId(sdkAppId);
        req.setSignName(signName);
        String[]templateParamSet;
        if(type == MessageDeliveryType.REGISTER){
            req.setTemplateId(registerTemplate);
            templateParamSet = new String[]{code, "10"};
            req.setTemplateParamSet(templateParamSet);
        }
        else{
            req.setTemplateId(loginedTemplate);
            templateParamSet = new String[]{code};
            req.setTemplateParamSet(templateParamSet);
        }
        SendSmsResponse resp = client.SendSms(req);
        if(!resp.getSendStatusSet()[0].getCode().equals("OK")){
            throw new RuntimeException(resp.getSendStatusSet()[0].getMessage());
        }
    }
}
