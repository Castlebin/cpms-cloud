package com.cpms.log.api.modules.log.feign.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/21 12:30
 */
@Component
public class ILogClientFallbackFactory implements FallbackFactory<ILogClientFallback> {
    @Override
    public ILogClientFallback create(Throwable throwable) {
        ILogClientFallback sysUserClientFallback = new ILogClientFallback();
        sysUserClientFallback.setThrowable(throwable);
        return  sysUserClientFallback;
    }
}
