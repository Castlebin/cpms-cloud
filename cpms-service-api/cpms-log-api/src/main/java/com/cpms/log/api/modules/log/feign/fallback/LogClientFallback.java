package com.cpms.log.api.modules.log.feign.fallback;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.log.api.modules.log.dto.HandlerLogDTO;
import com.cpms.log.api.modules.log.feign.ILogClient;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/21 12:30
 */
@Slf4j
public class LogClientFallback implements ILogClient {
    @Setter
    private Throwable throwable;

    @Override
    public Result<Void> recordHandlerLog(HandlerLogDTO handlerLogDTO) {
        log.error("[recordHandlerLog]熔断异常信息:",throwable);
        return  ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_BUSY_ERROR);
    }
}
