package com.cpms.log.api.modules.log.feign;

import com.cpms.common.constant.AppConstant;
import com.cpms.framework.common.core.api.Result;
import com.cpms.log.api.modules.log.dto.HandlerLogDTO;
import com.cpms.log.api.modules.log.feign.factory.LogClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description: 日志服务接口提供给其它微服务的feign调用接口
 * @author: gulang
 * @time: 2021/8/20 19:12
 */
@FeignClient(
        value = AppConstant.APPLICATION_LOG_NAME,
        fallbackFactory = LogClientFallbackFactory.class,
        path = AppConstant.LOG_API_PREFIX  // 接口前缀
)
public interface ILogClient {
    /**
     * api前缀
     */
    String API_PREFIX = "/log";
    /**
     *  系统后台用户登录
     * @param handlerLogDTO
     * @return
     */
    @PostMapping(API_PREFIX+"/record-handler-log")
    Result<Void> recordHandlerLog(@RequestBody HandlerLogDTO handlerLogDTO);
}
