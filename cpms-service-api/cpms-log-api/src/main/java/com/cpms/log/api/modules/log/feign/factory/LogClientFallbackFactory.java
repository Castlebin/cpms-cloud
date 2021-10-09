package com.cpms.log.api.modules.log.feign.factory;

import com.cpms.log.api.modules.log.feign.ILogClient;
import com.cpms.log.api.modules.log.feign.fallback.LogClientFallback;
import feign.hystrix.FallbackFactory;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/21 12:30
 */
public class LogClientFallbackFactory implements FallbackFactory<ILogClient> {
    @Override
    public LogClientFallback create(Throwable throwable) {
        LogClientFallback sysUserClientFallback = new LogClientFallback();
        sysUserClientFallback.setThrowable(throwable);
        return  sysUserClientFallback;
    }
}
