package com.cpms.framework.common.config;

import com.cpms.framework.common.exception.GlobalExceptionHandler;
import com.cpms.framework.common.utils.thread.ThreadPoolBuilder;
import com.cpms.framework.common.utils.CsSpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.concurrent.Executor;

/**
 * @description: 在公共模块，使用了spring注解的类，由于扫描不到，不能被Spring管理，所以需要@Configuration+@bean单独配置，
 * @author: gulang
 * @time: 2021/6/11 16:19
 */
@Configuration
@Order(-2147483648)
public class CoreCommonConfiguration {
    public CoreCommonConfiguration() {
    }

    @Bean
    public CsSpringUtil springUtil() {
        return new CsSpringUtil();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    /**
     * 自定义 @Async注解 指定默认使用的线程池
     * @return
     */
    @Bean("defaultAsyncTaskPool")
    public Executor defaultAsyncTaskPool(){
        return ThreadPoolBuilder.buildThreadPool(32);
    }
}
