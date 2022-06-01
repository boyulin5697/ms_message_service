package com.by.ms.message.service.api.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * Message feign apis
 * @author boyulin
 * @since 2022/6/1
 */
@Component
@FeignClient(value = "ms_message_service")
public interface MessageApis {


}
