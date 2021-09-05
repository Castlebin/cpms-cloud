package com.cpms.auth.authen;

import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsSpringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: gulang
 * @time: 2021/6/7 19:49
 */
public class UserAuthenBuilder {
    /**
     *  认证类型缓存池
     */
    private static Map<String, IAuthen> authenPool = new ConcurrentHashMap<>();
    static {
        authenPool.put(PasswordAuthen.GRANT_TYPE, CsSpringUtil.getBean(PasswordAuthen.class));
        authenPool.put(RefreshTokenAuthen.GRANT_TYPE, CsSpringUtil.getBean(RefreshTokenAuthen.class));
    }

    public static IAuthen getGrantType(String grantType){
        IAuthen iAuthen = authenPool.get(grantType);
        if(iAuthen == null) {
            throw new BizException("not grant Type was found");
        }
        return iAuthen;
    }
}
