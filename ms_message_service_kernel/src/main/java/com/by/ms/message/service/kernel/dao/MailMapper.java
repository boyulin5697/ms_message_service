package com.by.ms.message.service.kernel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.by.ms.message.service.kernel.entities.MailLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mail Mapper
 *
 * @author by.
 * @date 2022/4/30
 */
@Repository
@Mapper
public interface MailMapper extends BaseMapper<MailLogEntity> {
}
