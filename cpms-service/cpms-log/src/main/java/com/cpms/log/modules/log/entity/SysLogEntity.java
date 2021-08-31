package com.cpms.log.modules.log.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cpms.common.core.base.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/21 16:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("cpms_system_log")
public class SysLogEntity extends TenantEntity {
    private static final long serialVersionUID = 1L;
    /**
     *  指定自增策略
     */
    @TableId(value = "log_id",type = IdType.ASSIGN_ID)
    private Long logId;
    private String title;
    private String serviceName;
    private String handleIp;
    private String reqUrl;
    private String reqMethod;
    private String reqParams;
    private Long   exeTime;
    private String resultMsg;
}
