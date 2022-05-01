package com.by.ms.message.service.kernel;

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
    @TableId
    @Column(name = "ser_id",length = 32)
    private String id = UuidTool.getUUID();

    @Column(name = "ser_dest_email",length = 108)
    private String destEmail;

    @Column(name = "ser_send_time")
    private Date sendTime;

    @Column(name = "ser_delivery_type")
    private int deliveryType;

    @Column(name = "ser_send_status")
    private int sendStatus = MessageSendStatus.NOT_SEND;

    @Column(name = "ser_remark",length = 512)
    private String remark;
}
