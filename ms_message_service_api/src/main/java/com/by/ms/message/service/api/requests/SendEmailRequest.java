package com.by.ms.message.service.api.requests;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Send Email Request
 *
 * @author by.
 * @date 2022/4/29
 */
@Getter
@Setter
public class SendEmailRequest implements Serializable {
    /**
     * request email address
     */
    private String email;
    /**
     * delivery type
     */
    private int deliveryType;
}
