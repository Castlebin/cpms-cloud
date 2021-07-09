package com.cpms.system.api.feign.fallback;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.common.enums.GlobalResponseResultEnum;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.api.feign.ISysUserClient;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author gualng
 * @Description:  相关接口服务降级回调方法
 */
@Component
@Slf4j
public class ISysUserClientFallback implements ISysUserClient {
    @Setter
    private Throwable throwable;

    @Override
    public Result<SysUserLoginBO> sysUserLogin(SysUserLginDTO sysUserLginDTO) {
        log.error("[sysUserLogin]熔断异常信息:",throwable);
        return  ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_BUSY_ERROR);
    }
}
