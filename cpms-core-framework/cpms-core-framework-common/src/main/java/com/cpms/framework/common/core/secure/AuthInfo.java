package com.cpms.framework.common.core.secure;

import java.io.Serializable;

/**
 * @description: 授权用户信息
 * @author: gulang
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
    private long expire;

    private UserLoginBase userInfo;

    public AuthInfo() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public UserLoginBase getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserLoginBase userInfo) {
        this.userInfo = userInfo;
    }
}
