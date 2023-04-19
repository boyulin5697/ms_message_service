package com.by.ms.message.service.kernel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * MailBox
 *
 * @author by.
 * @date 2022/8/13
 */
@Data
public class MailBox implements Serializable {
    @MongoId
    private String id;
    /**
     * mail queue
     */
    private List<MessageInfo> messageQueue = new LinkedList<>();

    public static MessageInfo createMessageInfo(String messageId, int messageType, Date sendTime){
        return new MessageInfo(messageId,messageType,sendTime);
    }

    @Data
    @AllArgsConstructor
    public static class MessageInfo{

        private String messageId;

        private int messageType;

        private Date sendTime;

    }

}
