package com.by.ms.message.apis;

import com.by.ms.message.service.api.requests.SendMessageRequest;
import com.by.ms.message.service.api.responses.SendMessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Message feign apis
 * @author boyulin
 * @since 2022/6/1
 */
@Component
@FeignClient(value = "ms-message-service")
public interface MessageApis {

    /**
     * Send register email
     * @param request request
     * @return response response
     */
    @RequestMapping(value = "/rpc/requestRegisterEmail",method = RequestMethod.POST)
    SendMessageResponse<String> requestRegisterEmail(@RequestBody SendMessageRequest request);


    /**
     * Send register sms
     * @param request request
     * @return response response
     */
    @RequestMapping(value = "/rpc/request/requestRegisterSms",method = RequestMethod.POST)
    SendMessageResponse<String> requestRegisterSms(@RequestBody SendMessageRequest request);



}
