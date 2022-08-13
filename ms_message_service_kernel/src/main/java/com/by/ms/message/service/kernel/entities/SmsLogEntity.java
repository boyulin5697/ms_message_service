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
 * SMS repository entity
 *
 * @author by.
 * @date 2022/4/30
 */
@Entity
@Table(name = "send_sms_record")
@Data
@TableName(value = "send_sms_record")
public class SmsLogEntity implements Serializable {
    @Id
    @TableId(value = "ssr_id")
    private String id = UuidTool.getUUID();

    @TableField(value = "ssr_dest_number")
    private String destNumber;

    @TableField(value = "ssr_send_time")
    private Date sendTime;

    @TableField(value = "ssr_delivery_type")
    private int deliveryType;

    @TableField(value = "ssr_send_status")
    private int sendStatus = MessageSendStatus.NOT_SEND;

    @TableField(value = "ssr_remark")
    private String remark;
}
