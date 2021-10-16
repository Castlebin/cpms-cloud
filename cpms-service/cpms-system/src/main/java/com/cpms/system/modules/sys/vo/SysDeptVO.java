package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @description:
 * @author: gulang
 * @time: 2021/9/23 11:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptVO extends BaseVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;
    private String deptName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    private String tenantName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;
    private String superiorDept;
    private String deptDesc;
}