package com.by.ms.message.service.api.responses;

import lombok.Data;

/**
 * Response
 *
 * @author by.
 * @date 2022/6/1
 */
@Data
public class SendMessageResponse<T> {
    /**
     * status
     * @see com.by.ms.message.service.api.consts.StatusCode
     */
    private int status;
    /**
     * response data
     */
    private T data;
}
