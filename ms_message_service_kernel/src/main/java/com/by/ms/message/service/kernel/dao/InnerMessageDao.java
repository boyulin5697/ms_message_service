package com.by.ms.message.service.kernel.dao;

import com.by.commons.mongodb.StandardMongoOperations;
import com.by.ms.message.service.kernel.entities.InnerMessage;
import org.springframework.stereotype.Repository;

/**
 * Inner message dao
 *
 * @author by.
 * @date 2022/8/13
 */
@Repository
public class InnerMessageDao extends StandardMongoOperations<InnerMessage> {
}
