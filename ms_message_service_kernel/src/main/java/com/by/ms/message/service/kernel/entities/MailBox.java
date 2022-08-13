package com.by.ms.message.service.kernel.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * MailBox
 *
 * @author by.
 * @date 2022/8/13
 */
@Data
public class MailBox implements Serializable {
    @MongoId
    private String userNo;
    /**
     * mail queue
     */
    private Deque<String> messageQueue = new LinkedList<>();

}
