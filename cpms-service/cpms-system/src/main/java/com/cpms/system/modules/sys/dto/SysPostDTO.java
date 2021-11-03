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
 * @time: 2021/9/27 14:24
 */
@Data
public class SysPostDTO {
    @NotNull(message="postId不能为空",groups = {UpdateGroup.class, DeleteGroup.class})
    private Long postId;
    @NotBlank(message="postName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="postName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String postName;
    @NotBlank(message="postCode不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="postCode不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String postCode;
    private String postDesc;
    @NotNull(message="postSort不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Integer postSort;
}
