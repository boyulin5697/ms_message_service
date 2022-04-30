package com.by.ms.message.service.kernel.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.by.ms.message.service.kernel.consts.MessageType;
import com.by.ms.message.service.kernel.service.MailService;
import com.by.ms.message.service.kernel.service.SMSService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.List;

/**
 * Message Consume Processor
 *
 * @author by.
 * @date 2022/4/29
 */
@Component
@Slf4j
public class MessageServiceListenerProcessor implements MessageListenerConcurrently {

    private static final int MAX_RETRY_TIMES = 3;

    @Autowired
    private MailService mailService;

    @Autowired
    private SMSService smsService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if(CollectionUtils.isEmpty(list)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        try{
            JSONObject object = JSON.parseObject(new String(messageExt.getBody(), "UTF-8"));
            int messageType = object.getInteger("messageType");
            if(messageType== MessageType.EMAIL){
                mailService.send(object.getString("email"),object.getString("code"),object.getInteger("messageDeliveryType"));
            }
            else if(messageType==MessageType.SMS){

            }


        }catch(Exception e){
            if(messageExt.getReconsumeTimes()==MAX_RETRY_TIMES){
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }


}
