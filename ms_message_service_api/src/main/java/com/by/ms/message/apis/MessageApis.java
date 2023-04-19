package com.by.ms.message.apis;

import com.by.ms.message.service.api.requests.SendBatchInnerMessageRequest;
import com.by.ms.message.service.api.requests.SendInnerMessageRequest;
import com.by.ms.message.service.api.requests.SendMessageRequest;
import com.by.ms.message.service.api.responses.SendMessageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Message feign apis
 * @author by.
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


    /**
     * Send Inner Message
     * @param request Send Inner Message Request
     * @return response response
     */
    @RequestMapping(value = "/rpc/sendInnerMessage",method = RequestMethod.POST)
    SendMessageResponse<String> sendInnerMessage(@RequestBody SendInnerMessageRequest request);

    /**
     * Send inner messages to a batch of users
     * @param request
     * @return
     */
    @RequestMapping(value = "rpc/sendBatchInnerMessage", method = RequestMethod.POST)
    SendMessageResponse<String> sendBatchInnerMessage(@RequestBody SendBatchInnerMessageRequest request);


}
