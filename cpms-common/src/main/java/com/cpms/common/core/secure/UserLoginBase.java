package com.cpms.common.core.secure;

import java.io.Serializable;

/**
 * @description: 登录用户信息基础类
 * @author: 01396003
 * @time: 2021/6/7 19:30
 */
public class UserLoginBase implements Serializable{
    /**
     * 用户userId
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户登录ip
     */
    private String loginIp;

    /**
     * 用户登录时间
     */
    private String loginTime;

    /**
     * 用户头像
     */
    private String avatar;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
