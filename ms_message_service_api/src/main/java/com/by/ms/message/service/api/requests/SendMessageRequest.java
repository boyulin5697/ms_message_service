package com.by.ms.message.service.api.requests;

import lombok.Data;

import java.io.Serializable;

/**
 * rpc send message request
 *
 * @author by.
 * @date 2022/6/1
 */
@Data
public class SendMessageRequest implements Serializable {
    /**
     * destination address
     */
    private String destination;
    /**
     * send type: 1. email, 2.sms
     */
    private int sendType;
    /**
     * deliverType
     * @see com.by.ms.message.service.kernel.consts
     */
    private int deliveryType;
}
