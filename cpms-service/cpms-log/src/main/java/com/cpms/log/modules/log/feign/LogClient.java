package com.cpms.log.modules.log.feign;

import com.cpms.common.core.api.Result;
import com.cpms.log.api.modules.log.dto.HandlerLogDTO;
import com.cpms.log.api.modules.log.feign.ILogClient;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/21 12:49
 */
public class LogClient implements ILogClient {
    @Override
    public Result<Void> recordHandlerLog(HandlerLogDTO handlerLogDTO) {
        
        return null;
    }
}
