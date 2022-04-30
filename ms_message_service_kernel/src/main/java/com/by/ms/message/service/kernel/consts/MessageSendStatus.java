package com.by.ms.message.service.kernel.consts;

/**
 * Message Send Status
 *
 * @author by.
 * @date 2022/4/30
 */
public interface MessageSendStatus {
    /**
     * not send yet
     */
    int NOT_SEND = 0;
    /**
     * sending
     */
    int SENDING = 1;
    /**
     * successfully send
     */
    int SUCCESS = 2;
    /**
     * send failed
     */
    int FAILED = 3;
}
