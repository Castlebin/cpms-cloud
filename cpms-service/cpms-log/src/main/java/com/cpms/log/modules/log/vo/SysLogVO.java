package com.cpms.log.modules.log.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 16:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysLogVO extends BaseVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long logId;
    private String title;
    private String serviceName;
    private String handleIp;
    private String reqUrl;
    private String reqMethod;
    private String reqParams;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long   exeTime;
    private String resultMsg;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;
}
