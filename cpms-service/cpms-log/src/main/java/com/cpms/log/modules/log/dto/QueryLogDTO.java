package com.cpms.log.modules.log.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 16:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryLogDTO extends BasePageDTO {
    private String title;
    private String serviceName;
    private String handleIp;
    private String reqUrl;
    private String createBy;
    /**
     * 租户ID
     */
    private Long tenantId;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime handleStart;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime handleEnd;
}
