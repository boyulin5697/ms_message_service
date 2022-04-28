package com.by.ms.message.service.kernel.mail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * Email sending service
 *
 * @author by.
 * @date 2022/4/29
 */
@Service
@RefreshScope
public class MailService {
    @Value("${ms.email.hostName}")
    private String hostName;

    @Value("${ms.email.smtpPort}")
    private int smtpPort;

    @Value("${ms.email.charset")
    private String charset;

    @Value("${ms.email.sourceAuth}")
    private String sourceAuth;


}
