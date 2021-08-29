package com.cpms.log.api.modules.log.feign.fallback;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.common.enums.GlobalResponseResultEnum;
import com.cpms.log.api.modules.log.dto.HandlerLogDTO;
import com.cpms.log.api.modules.log.feign.ILogClient;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/21 12:30
 */
@Component
@Slf4j
public class ILogClientFallback  implements ILogClient {
    @Setter
    private Throwable throwable;

    @Override
    public Result<Void> recordHandlerLog(HandlerLogDTO handlerLogDTO) {
        log.error("[recordHandlerLog]熔断异常信息:",throwable);
        return  ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_BUSY_ERROR);
    }
}