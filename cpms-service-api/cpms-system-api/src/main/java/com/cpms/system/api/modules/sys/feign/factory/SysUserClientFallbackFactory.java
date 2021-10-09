package com.cpms.system.api.modules.sys.feign.factory;

import com.cpms.system.api.modules.sys.feign.ISysUserClient;
import com.cpms.system.api.modules.sys.feign.fallback.SysUserClientFallback;
import feign.hystrix.FallbackFactory;

/**
 * @description: 回调工厂方法可以捕获熔断异常
 * @author: gulang
 * @time: 2021/6/24 17:05
 */
public class SysUserClientFallbackFactory implements FallbackFactory<ISysUserClient> {
    @Override
    public SysUserClientFallback create(Throwable throwable) {
        SysUserClientFallback sysUserClientFallback = new SysUserClientFallback();
        sysUserClientFallback.setThrowable(throwable);
        return  sysUserClientFallback;
    }
}
