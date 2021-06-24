package com.cpms.auth.authen;

import com.cpms.common.exception.BizException;
import com.cpms.common.utils.SpringUtil;
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
        authenPool.put(SysAdminAuthen.AUTHEN_TYPE, SpringUtil.getBean(SysAdminAuthen.class));
        authenPool.put(WxMiniAuthen.AUTHEN_TYPE, SpringUtil.getBean(WxMiniAuthen.class));
    }

    public static IAuthen getAuthenType(String authenType){
        IAuthen iAuthen = authenPool.get(authenType);
        if(iAuthen == null) {
            throw new BizException("not authen Type was found");
        }
        return iAuthen;
    }
}
