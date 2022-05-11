package com.by.ms.message.service.api;


import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "email(邮箱)",required = true)
    private String email;
    @ApiModelProperty(value = "deliveryType(发送信息类型)",required = true)
    private int deliveryType;
}
