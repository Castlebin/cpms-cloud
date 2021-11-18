package com.cpms.log.modules.log.feign;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.utils.CsBeanUtil;
import com.cpms.log.api.modules.log.dto.HandlerLogDTO;
import com.cpms.log.api.modules.log.feign.ILogClient;
import com.cpms.log.modules.log.entity.SysLogEntity;
import com.cpms.log.modules.log.service.ISysLogService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/21 12:49
 */
@RestController
public class LogClient implements ILogClient {
    @Resource
    private ISysLogService sysLogService;

    @Override
    @PostMapping(API_PREFIX+"/record-handler-log")
    public Result<Void> recordHandlerLog(HandlerLogDTO handlerLogDTO) {
        SysLogEntity sysLogEntity = new SysLogEntity();
        CsBeanUtil.copyProperties(handlerLogDTO,sysLogEntity);
        sysLogService.save(sysLogEntity);
        return ResultUtil.success();
    }
}
