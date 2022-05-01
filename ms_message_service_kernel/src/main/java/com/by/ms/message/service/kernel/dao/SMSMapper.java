package com.by.ms.message.service.kernel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.by.ms.message.service.kernel.SmsLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * SMS Mapper
 *
 * @author by.
 * @date 2022/5/1
 */
@Repository
@Mapper
public interface SMSMapper extends BaseMapper<SmsLogEntity> {
}
