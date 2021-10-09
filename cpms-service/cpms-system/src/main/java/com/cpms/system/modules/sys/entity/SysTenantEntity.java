package com.cpms.system.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.framework.mybatis.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 11:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cpms_system_tenant")
public class SysTenantEntity extends BaseEntity {

    @TableId(value = "tenant_id",type = IdType.ASSIGN_ID)
    private Long tenantId;
    private String tenantName;
    private String contacts;
    private String contactNumber;
    private String address;
    private Integer tenantStatus;
    private String accountPrefix;
    private String tenantCode;
    private String tenantDesc;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeStart;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private LocalDateTime leaseTimeEnd;

}
