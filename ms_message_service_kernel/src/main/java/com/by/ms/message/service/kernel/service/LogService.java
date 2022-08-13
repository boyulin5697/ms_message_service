package com.by.ms.message.service.kernel.service;

import com.by.ms.message.service.kernel.entities.MailLogEntity;
import com.by.ms.message.service.kernel.entities.SmsLogEntity;
import com.by.ms.message.service.kernel.dao.MailMapper;
import com.by.ms.message.service.kernel.dao.SMSMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sending Log service
 * We aim the log system should not influence the normal operation of sending message process, so
 * any kind of exceptions relate to this sevice must be handled in the log service level only.
 *
 * @author by.
 * @date 2022/5/1
 */
@Service
@Slf4j
public class LogService {
    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private SMSMapper smsMapper;


    /**
     * get email logs
     * @param id
     * @return
     */
    public MailLogEntity getMailLog(String id){
        try {
            MailLogEntity mailLogEntity = mailMapper.selectById(id);
            if (null == mailLogEntity) {
                return null;
            } else {
                return mailLogEntity;
            }
        }catch (Exception e){
            log.error("Meeting error when getting mail log",e);
            return null;
        }
    }

    /**
     * get sms log
     * @param id
     * @return
     */
    public SmsLogEntity getSMSLog(String id){
        try {
            SmsLogEntity smsLogEntity = smsMapper.selectById(id);
            if (null == smsLogEntity) {
                return null;
            } else {
                return smsLogEntity;
            }
        }catch (Exception e){
            log.error("Meeting error when getting sms log",e);
            return null;
        }
    }

    /**
     * Save mail logs
     * @param entity
     */
    public void save(MailLogEntity entity){
        try{
            mailMapper.updateById(entity);
        }catch (Exception e){
            log.error("There is something wrong when updating mail log",e);
        }
    }


    /**
     * Save sms logs
     * @param entity
     */
    public void save(SmsLogEntity entity){
        try{
            smsMapper.updateById(entity);
        }catch (Exception e){
            log.error("There is something wrong when updating sms log",e);
        }
    }


}
