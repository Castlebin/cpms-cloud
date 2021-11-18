package com.cpms.system.modules.sys.dto;

import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.SelectGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @NotNull(message="userId不能为空",groups = {UpdateGroup.class, DeleteGroup.class, SelectGroup.class})
    private Long userId;

    /**
     * 所属租户
     */
    @NotNull(message="tenantId不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Long tenantId;
    /**
     * 用户昵称
     */
    @NotBlank(message="userName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="userName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String userName;

    /**
     * 真实姓名
     */
    @NotBlank(message="userRealName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="userRealName不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String userRealName;

    /**
     * 用户生日
     */
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @JsonFormat( pattern = "yyyy-MM-dd" ,timezone="GMT+8")
    @NotNull(message="userBirthday不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private LocalDate userBirthday;

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
     * 角色ID集合
     */
    @NotNull(message="roleIds不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotEmpty(message="roleIds不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private List<String> roleIds;

    /**
     * 岗位ID
     */
    @NotNull(message="postId不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Long postId;

    /**
     * 用户手机号
     */
    @NotBlank(message="userMobile不能为空",groups = {UpdateGroup.class, AddGroup.class})
    @NotNull(message="userMobile不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private String userMobile;
    /**
     * 用户性别：0-未知，1-男，2-女
     */
    @NotNull(message="userSex不能为空",groups = {UpdateGroup.class, AddGroup.class})
    private Integer  userSex;

    /**
     * 用户头像
     */
    private String userAvatar;

}
