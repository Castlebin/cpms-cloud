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
 * @time: 2021/7/21 14:43
 */
@Data
public class SysUserDTO {
    /**
     * 用户userId
     */
    @NotNull(message="userId不能为空",groups = {UpdateGroup.class, DeleteGroup.class})
    private Long userId;

    /**
     * 用户名
     */
    @NotBlank(message="userName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="userName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String userName;

    /**
     * 用户账号
     */
    @NotBlank(message="userAccount不能为空",groups = {AddGroup.class})
    @NotNull(message="userAccount不能为空",groups = {AddGroup.class})
    private String userAccount;

    /**
     * 用户密码
     */
    @NotBlank(message="userPassword不能为空",groups = {AddGroup.class})
    @NotNull(message="userPassword不能为空",groups = {AddGroup.class})
    private String userPassword;

    /**
     * 部门ID
     */
    @NotNull(message="deptId不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Long deptId;

    /**
     * 角色ID，多个使用英文逗号分隔
     */
    @NotBlank(message="roleIds不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="roleIds不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String roleIds;

    /**
     * 岗位ID
     */
    @NotNull(message="postId不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Long postId;

    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 用户性别：0-未知，1-男，2-女
     */
    private Integer  userSex;

    /**
     * 用户头像
     */
    private String userAvatar;

}
