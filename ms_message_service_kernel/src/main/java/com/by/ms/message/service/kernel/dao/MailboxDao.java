package com.by.ms.message.service.kernel.dao;

import com.by.commons.mongodb.StandardMongoOperations;
import com.by.ms.message.service.kernel.entities.MailBox;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ,,,
 *
 * @author by.
 * @date 2022/8/13
 */
@Repository
public class MailboxDao extends StandardMongoOperations<MailBox> {
    public List<MailBox> getAllData(){
       return mongoTemplate.findAll(MailBox.class,COLLECTION);
    }
}
