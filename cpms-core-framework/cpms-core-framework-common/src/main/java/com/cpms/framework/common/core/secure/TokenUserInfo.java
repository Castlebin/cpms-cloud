package com.cpms.framework.common.core.secure;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.List;

/**
 * @description: token包含的用户信息
 * @author: gulang
 * @time: 2021/7/22 19:10
 */
public class TokenUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userAccount;
    private String userName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String userMobile;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantId;
    private int userSex;
    private List<String> roleCodes;
    @JsonSerialize(using = ToStringSerializer.class)
    private List<Long> roleIds;
    /**
     *  token过期截止时间，单位：秒
     */
    private Long tokenExpire;
    private String tenantCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long postId;

    public TokenUserInfo() {
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Long tokenExpire) {
        this.tokenExpire = tokenExpire;
    }



    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    @Override
    public String toString() {
        return "TokenUserInfo{" +
                "userAccount='" + userAccount + '\'' +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", userMobile='" + userMobile + '\'' +
                ", deptId=" + deptId +
                ", tenantId=" + tenantId +
                ", userSex=" + userSex +
                ", roleCodes=" + roleCodes +
                ", roleIds=" + roleIds +
                ", tokenExpire=" + tokenExpire +
                ", tenantCode='" + tenantCode + '\'' +
                ", postId=" + postId +
                '}';
    }
}
