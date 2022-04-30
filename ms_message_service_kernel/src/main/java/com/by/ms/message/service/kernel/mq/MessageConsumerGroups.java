package com.by.ms.message.service.kernel.mq;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Email consumer groups
 *
 * @author by.
 * @date 2022/4/29
 */
@Data
@Configuration
@RefreshScope
@Slf4j
public class MessageConsumerGroups {
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.name-server}")
    private String nameServer;
    private Integer consumeThreadMin = 15;
    private Integer consumeThreadMax = 32;
    private Integer consumeMessageBatchMaxSize = 1;

    @Autowired
    private MessageServiceListenerProcessor listenerProcessor;

    @Bean
    public DefaultMQPushConsumer messageConsumer()throws MQClientException{
        log.info("===Message consumer is creating...===");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(groupName);
        consumer.setNamesrvAddr(nameServer);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.registerMessageListener(listenerProcessor);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        try{
            consumer.subscribe("topic_message_service","*");
            consumer.start();
            log.info("===Message consumer create succeed!===");
        }catch (MQClientException e){
            log.info("===Message consumer create failed!===",e);
        }
        return consumer;

    }

}
