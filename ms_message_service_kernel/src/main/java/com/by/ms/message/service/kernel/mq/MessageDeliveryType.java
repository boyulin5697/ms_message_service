package com.by.ms.message.service.kernel.mq;

/**
 * message delivery type
 *
 * @author by.
 * @date 2022/4/29
 */
public interface MessageDeliveryType {
    int REGISTER = 1;
    int LOGIN_VERIFICATION = 2;
    int MODIFY_INFO = 3;
}
