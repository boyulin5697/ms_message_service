package com.by.ms.message.service.kernel.mail.service;

import com.by.commons.communication.StandardResp;
import com.by.commons.consts.ResponseCodeEnum;
import com.by.commons.mq.RocketmqProducer;
import com.by.commons.tools.GenerateCodeTool;
import com.by.ms.message.service.api.SendEmailRequest;
import com.by.ms.message.service.kernel.mq.MessageDeliveryType;
import com.by.ms.message.service.kernel.mq.MessageType;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Email sending service
 *
 * @author by.
 * @date 2022/4/29
 */
@Service
@RefreshScope
public class MailService {
    private static final String topic = "topic_message_service";
    @Value("${ms.email.hostName}")
    private String hostName;

    @Value("${ms.email.smtpPort}")
    private int smtpPort;

    @Value("${ms.email.charset")
    private String charset;

    @Value("${ms.email.sourceAuth}")
    private String sourceAuth;

    @Value("${ms.email.sourceEmail}")
    private String sourceEmail;

    @Autowired
    private RocketmqProducer producer;

    public StandardResp sendMail(SendEmailRequest emailRequest, int deliveryType){
        try {
            Assert.isTrue(!StringUtils.hasText(emailRequest.getEmail()), "Email cannot be empty!");
        }catch (IllegalArgumentException e){
            return new StandardResp().error(ResponseCodeEnum.REQUEST_ERROR,e.getMessage());
        }
        String code = GenerateCodeTool.generateCode(6);
        Map<String,Object>params = new HashMap<>();
        params.put("messageType", MessageType.EMAIL);
        params.put("messageDeliveryType", deliveryType);
        params.put("email",emailRequest.getEmail());
        params.put("code",code);
        producer.asyncSend(topic,params);
        return new StandardResp<>().success(code,"Successfully send code!");
    }

    public void sendVerify(String email, String code, int type) throws EmailException {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName(this.hostName);
        mail.setSmtpPort(this.smtpPort);
        mail.setSSLOnConnect(true);
        mail.setSslSmtpPort(String.valueOf(this.smtpPort));
        mail.setCharset(this.charset);
        mail.addTo(email);
        mail.setFrom(this.sourceEmail);
        mail.setAuthentication(this.sourceEmail,this.sourceAuth);
        mail.setSubject("Please check your verify code!");
        mail.send();
    }

}

