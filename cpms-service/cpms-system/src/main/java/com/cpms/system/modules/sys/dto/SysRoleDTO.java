package com.cpms.system.modules.sys.dto;

import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 17:02
 */
@Data
public class SysRoleDTO{
    @NotNull(message="roleId不能为空",groups = {UpdateGroup.class, DeleteGroup.class})
    private Long roleId;

    @NotBlank(message="userName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="userName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String roleName;

    @NotBlank(message="roleCode不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="roleCode不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String roleCode;
    private Integer roleSort;
    private String roleDesc;
}
