package com.by.ms.message.service.kernel.requests;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Send SMS request
 *
 * @author by.
 * @date 2022/4/30
 */
@Getter
@Setter
public class SendSMSRequest implements Serializable {
    private String telephone;
    private int deliveryType;
}
