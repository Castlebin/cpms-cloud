package com.cpms.system.modules.sys.dto;

import com.cpms.framework.common.core.base.BasePageDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 15:44
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryTenantDTO extends BasePageDTO {
    private String tenantName;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeStart;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeEnd;

    private String tenantCode;
    private String accountPrefix;
    private Integer tenantStatus;
}
