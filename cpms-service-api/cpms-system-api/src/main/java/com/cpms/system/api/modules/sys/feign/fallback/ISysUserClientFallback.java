package com.cpms.system.api.modules.sys.feign.fallback;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.api.modules.sys.feign.ISysUserClient;

import com.cpms.system.api.modules.sys.vo.SysUserLoginVO;
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
    public Result<SysUserLoginVO> sysUserLogin(SysUserLginDTO sysUserLginDTO) {
        log.error("[sysUserLogin]熔断异常信息:",throwable);
        return  ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_BUSY_ERROR);
    }
}
