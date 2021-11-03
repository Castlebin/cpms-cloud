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
 * @time: 2021/9/23 11:05
 */
@Data
public class SysDeptDTO {
    @NotNull(message="deptId不能为空",groups = {UpdateGroup.class, DeleteGroup.class})
    private Long deptId;

    @NotBlank(message="deptName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="deptName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String deptName;

    @NotNull(message="parentId不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Long parentId;
    private String deptDesc;
    @NotNull(message="deptSort不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Integer deptSort;
}
