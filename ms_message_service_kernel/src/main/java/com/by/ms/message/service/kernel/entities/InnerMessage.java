package com.by.ms.message.service.kernel.entities;


import com.by.commons.tools.UuidTool;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Inner message
 *
 * @author by.
 * @date 2022/8/13
 */
@Data
public class InnerMessage implements Serializable {
    /**
     * Message id
     */
    @MongoId
    private String id = UuidTool.getUUID();

    /**
     * Message Source
     */
    private String sourceId;

    /**
     * The destination id
     */
    private List<String> destId;

    /**
     * Message title
     */
    private String title;

    /**
     * The content of the message
     */
    private String content;

    /**
     * The time message send
     */
    private Date sendTime;

    /**
     * @see com.by.ms.message.service.api.consts.InnerMessageTypes
     */
    private Integer innerMessageType;


}
