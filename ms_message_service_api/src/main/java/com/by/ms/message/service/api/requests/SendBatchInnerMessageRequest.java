package com.by.ms.message.service.api.requests;

import com.by.commons.apis.ApiRequest;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ,,,
 *
 * @author by.
 * @date 2023/4/19
 */
@Data
public class SendBatchInnerMessageRequest implements Serializable, ApiRequest {

    /**
     * Sender's Id
     */
    private String sourceId;

    /**
     * Receiver's Id
     */
    private List<String> destId;

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
        Assert.isTrue(destId.size()>1, "Batch sending must have more then 1 destinations!");
        Assert.isTrue(content.length()<255,"The content length excceed");
    }

}
