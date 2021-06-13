package com.cpms.system.api.feign;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.common.enums.GlobalResponseResultEnum;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import org.springframework.stereotype.Component;

/**
 * @author gualng
 * @Description:  相关接口服务降级回调方法
 */
@Component
public class ISysUserClientFallback implements ISysUserClient {
    @Override
    public Result<SysUserLoginBO> sysUserLogin(SysUserLginDTO sysUserLginDTO) {
        return ResultUtil.error(GlobalResponseResultEnum.INTERNAL_SERVER_ERROR);
    }
}
