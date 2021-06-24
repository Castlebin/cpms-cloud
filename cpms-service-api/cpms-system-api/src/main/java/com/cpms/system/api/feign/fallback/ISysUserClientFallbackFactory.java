package com.cpms.system.api.feign.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 回调工厂方法可以捕获熔断异常
 * @author: gulang
 * @time: 2021/6/24 17:05
 */
@Component
public class ISysUserClientFallbackFactory implements FallbackFactory<ISysUserClientFallback> {
    @Override
    public ISysUserClientFallback create(Throwable throwable) {
        ISysUserClientFallback sysUserClientFallback = new ISysUserClientFallback();
        sysUserClientFallback.setThrowable(throwable);
        return  sysUserClientFallback;
    }
}
