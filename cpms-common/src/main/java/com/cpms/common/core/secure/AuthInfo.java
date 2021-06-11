package com.cpms.common.core.secure;

import java.io.Serializable;

/**
 * @description: 授权用户信息
 * @author: 01396003
 * @time: 2021/6/8 14:56
 */
public class AuthInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 生成的accessToken字符串
     */
    private String accessToken;
    /**
     * 过期时间，单位：秒
     */
    private long expireIn;

    private UserLoginBase userInfo;


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }

    public UserLoginBase getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserLoginBase userInfo) {
        this.userInfo = userInfo;
    }
}
