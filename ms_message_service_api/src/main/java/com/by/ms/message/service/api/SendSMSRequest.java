package com.by.ms.message.service.api;

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
    //@ApiModelProperty(value = "telephone(电话号码)",required = true)
    private String telephone;
    //@ApiModelProperty(value = "deliveryType(发送信息类型)",required = true)
    private int deliveryType;
}
