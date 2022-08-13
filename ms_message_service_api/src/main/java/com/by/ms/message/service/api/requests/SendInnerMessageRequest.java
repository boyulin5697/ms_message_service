package com.by.ms.message.service.api.requests;

import com.by.commons.apis.ApiRequest;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Send Inner Message Request
 *
 * @author by.
 * @date 2022/8/13
 */
@Data
public class SendInnerMessageRequest implements ApiRequest {

    /**
     * Sender's Id
     */
    private String sourceId;

    /**
     * Receiver's Id
     */
    private String destId;

    /**
     * message title
     */
    private String title;

    /**
     * Message Content
     */
    private String content;

    /**
     * Send Time
     */
    private Date sendTime = new Date();

    /**
     * @see com.by.ms.message.service.api.consts.InnerMessageTypes
     */
    private Integer innerMessageType;

    @Override
    public void verify() {
        Assert.isTrue(content.length()<255,"The content length excceed");
    }
}
