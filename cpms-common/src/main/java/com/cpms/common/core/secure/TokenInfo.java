package com.cpms.common.core.secure;

/**
 * @description:
 * @author: 01396003
 * @time: 2021/6/7 17:24
 */
public class TokenInfo {
    /**
     * 生成的token字符串
     */
    private String token;
    /**
     * token 有效期
     */
    private long expire;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
