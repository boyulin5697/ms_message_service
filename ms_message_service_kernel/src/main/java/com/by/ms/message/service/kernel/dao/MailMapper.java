package com.by.ms.message.service.kernel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.by.ms.message.service.kernel.MailEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mail Mapper
 *
 * @author by.
 * @date 2022/4/30
 */
@Mapper
public interface MailMapper extends BaseMapper<MailEntity> {
}
