package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @description:
 * @author: gulang
 * @time: 2021/9/15 16:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleVO extends BaseVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;
    private String deptName;
    private String roleName;
    private String roleCode;
    private String roleDesc;

}
