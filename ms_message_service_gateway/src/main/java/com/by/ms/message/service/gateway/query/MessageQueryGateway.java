package com.by.ms.message.service.gateway.query;

import com.by.commons.annotations.CheckProperties;
import com.by.commons.communication.StandardResp;
import com.by.ms.message.service.kernel.requests.innermessage.QueryMessageContentRequest;
import com.by.ms.message.service.kernel.service.InnerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Message Query Gateway
 *
 * @author by.
 * @date 2022/8/13
 */
@CrossOrigin
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageQueryGateway {

    @Autowired
    private InnerMessageService innerMessageService;

    /**
     * Query Mailbox and its detail information
     * @param request
     * @return
     */
    @CheckProperties
    @RequestMapping("/nt/queryInnerMailBox")
    public StandardResp queryMailBox(HttpServletRequest request){
        return innerMessageService.queryMailBox();
    }

    @CheckProperties
    @RequestMapping("/nt/queryInnerMessageContent")
    public StandardResp queryMessageContent(HttpServletRequest servletRequest, @RequestBody QueryMessageContentRequest request){
        return innerMessageService.queryMessage(request);
    }

}
