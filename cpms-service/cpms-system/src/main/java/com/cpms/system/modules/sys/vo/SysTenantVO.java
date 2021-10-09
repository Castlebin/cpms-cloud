package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 15:04
 */
@Data
public class SysTenantVO extends BaseVO {
    private Long tenantId;
    private String tenantName;
    private String contacts;
    private String contactNumber;
    private String address;
    private Integer tenantStatus;
    private String tenantCode;
    private String accountPrefix;
    private String tenantDesc;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeStart;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeEnd;
}
