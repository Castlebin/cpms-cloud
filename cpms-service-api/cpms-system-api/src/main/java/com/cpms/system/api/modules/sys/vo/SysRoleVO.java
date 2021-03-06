package com.cpms.system.api.modules.sys.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author gulang
 * @Description:
 * @time: 2021/8/7 13:15
 */
@Data
public class SysRoleVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;
    private String roleName;
    private String roleCode;
}
