package com.cpms.system.modules.sys.vo;

import com.cpms.framework.common.core.base.BaseVO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


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
    private Long tenantId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private Integer roleSort;
    private String menuIds;

}
