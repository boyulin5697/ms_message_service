package com.by.ms.message.service.kernel.requests.innermessage;

import com.by.commons.apis.ApiRequest;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * ,,,
 *
 * @author by.
 * @date 2022/8/13
 */
@Data
public class QueryMessageContentRequest implements ApiRequest, Serializable {

    /**
     * The message id query for
     */
    private String messageId;

    @Override
    public void verify() {
        Assert.hasText(messageId,"MessageId cannot be null!");
    }
}
