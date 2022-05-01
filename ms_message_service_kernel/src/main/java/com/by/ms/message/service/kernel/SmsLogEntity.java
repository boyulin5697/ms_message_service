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
    @TableId
    @Column(name = "ssr_id",length = 32)
    private String id = UuidTool.getUUID();

    @Column(name = "ssr_dest_number",length = 108)
    private String destNumber;

    @Column(name = "ssr_send_time")
    private Date sendTime;

    @Column(name = "ssr_delivery_type")
    private int deliveryType;

    @Column(name = "ssr_send_status")
    private int sendStatus = MessageSendStatus.NOT_SEND;

    @Column(name = "ssr_remark",length = 512)
    private String remark;
}
