package com.by.ms.message.service.kernel.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.by.commons.communication.StandardResp;
import com.by.commons.consts.ResponseCodeEnum;
import com.by.commons.mq.RocketmqProducer;
import com.by.commons.tools.GenerateCodeTool;
import com.by.ms.message.service.api.requests.SendEmailRequest;
import com.by.ms.message.service.kernel.MailLogEntity;
import com.by.ms.message.service.kernel.dao.MailMapper;
import com.by.ms.message.service.kernel.consts.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
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
@Slf4j
public class MailService extends ServiceImpl<MailMapper,MailLogEntity> {
    private static final String topic = "topic_message_service";
    @Value("${ms.email.hostName}")
    private String hostName;

    @Value("${ms.email.smtpPort}")
    private int smtpPort;

    @Value("${ms.email.charset}")
    private String charset;

    @Value("${ms.email.sourceAuth}")
    private String sourceAuth;

    @Value("${ms.email.sourceEmail}")
    private String sourceEmail;

    @Autowired
    private RocketmqProducer producer;

    @Autowired
    private MailMapper mailMapper;

    /**
     * Send Email Service
     * @TODO: Future extending function to all kinds of contents.
     * @param emailRequest
     * @return
     */
    public StandardResp sendCodeMail(SendEmailRequest emailRequest){
        try {
            Assert.isTrue(StringUtils.hasText(emailRequest.getEmail()), "Email cannot be empty!");
        }catch (IllegalArgumentException e){
            return new StandardResp().error(ResponseCodeEnum.REQUEST_ERROR,e.getMessage());
        }
        String code = GenerateCodeTool.generateCode(6);
        MailLogEntity mail = new MailLogEntity();
        Map<String,Object>params = new HashMap<>();
        params.put("messageType", MessageType.EMAIL);
        params.put("messageDeliveryType", emailRequest.getDeliveryType());
        params.put("email",emailRequest.getEmail());
        params.put("code",code);
        params.put("mailId",mail.getId());
        producer.asyncSend(topic,params);
        try {
            mail.setDestEmail(emailRequest.getEmail());
            mail.setSendTime(new Date());
            mail.setDeliveryType(emailRequest.getDeliveryType());
            mailMapper.insert(mail);
        }catch (Exception e){
            log.error("There is something wrong with recording email record",e);
        }
        return new StandardResp<>().success(code,"Successfully send code!");
    }

    /**
     * Send: code
     * @param email
     * @param code
     * @param type
     * @throws EmailException
     */
    public void send(String email, String code, int type) throws EmailException {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName(this.hostName);
        mail.setSmtpPort(this.smtpPort);
        mail.setSSLOnConnect(true);
        mail.setSslSmtpPort(String.valueOf(this.smtpPort));
        mail.setCharset(this.charset);
        mail.addTo(email);
        mail.setTextMsg("Your verify code is: "+code+", please submit in 10 minutes.");
        mail.setFrom(this.sourceEmail);
        mail.setAuthentication(this.sourceEmail,this.sourceAuth);
        mail.setSubject("Please check your verify code!");
        mail.send();
    }



}

