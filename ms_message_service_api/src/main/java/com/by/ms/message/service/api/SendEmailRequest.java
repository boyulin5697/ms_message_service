package com.by.ms.message.service.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Send Email Request
 *
 * @author by.
 * @date 2022/4/29
 */
@Getter
@Setter
public class SendEmailRequest {
    @ApiModelProperty(value = "Email(邮箱)",required = true)
    private String email;
}
