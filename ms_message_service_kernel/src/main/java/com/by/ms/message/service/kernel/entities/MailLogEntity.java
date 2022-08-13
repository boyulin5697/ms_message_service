package com.by.ms.message.service.kernel.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.by.commons.tools.UuidTool;
import com.by.ms.message.service.kernel.consts.MessageSendStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * email entity
 *
 * @author by.
 * @date 2022/4/30
 */
@Table(name = "send_email_record")
@TableName(value = "send_email_record")
@Entity
@Data
public class MailLogEntity implements Serializable {
    @Id
    @TableId(value = "ser_id")
    private String id = UuidTool.getUUID();

    @TableField(value = "ser_dest_email")
    private String destEmail;

    @TableField(value = "ser_send_time")
    private Date sendTime;

    @TableField(value = "ser_delivery_type")
    private int deliveryType;

    @TableField(value = "ser_send_status")
    private int sendStatus = MessageSendStatus.NOT_SEND;

    @TableField(value = "ser_remark")
    private String remark;
}
