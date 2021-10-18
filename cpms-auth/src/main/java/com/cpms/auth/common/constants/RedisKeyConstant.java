package com.cpms.auth.common.constants;

/**
 * @description: redis redis缓存key常量
 * @author: gulang
 * @time: 2021/5/25 16:01
 */
public class RedisKeyConstant {
    /**
     * redis key 命名规范：<服务名称>:<业务类型>:<唯一值KEY>:<唯一值VALUE>
     */
    private static final String  KEY_PREFIX = "authServer:";
    public static class UserLogin {
        public static final String  CAPTCHA_KEY = KEY_PREFIX+"userLogin:captcha:%s";
    }
}
