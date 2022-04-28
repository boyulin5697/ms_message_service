package com.by.message.service.gateway.controllers;


import com.by.commons.communication.StandardResp;
import com.by.commons.consts.ResponseCodeEnum;
import com.by.message.service.gateway.requests.SendEmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Message Controller
 *
 * @author by.
 * @date 2022/4/28
 */
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/message")
public class MessageController {

    @RequestMapping("/getRegisterEmail")
    public StandardResp getRegisterEmail(@ModelAttribute SendEmailRequest emailRequest){
        try{
            return null;
        }catch (Exception e){
            return new StandardResp().error("请求发送注册邮件失败");
        }
    }
}
