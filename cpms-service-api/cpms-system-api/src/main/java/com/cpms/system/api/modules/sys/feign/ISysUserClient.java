package com.cpms.system.api.modules.sys.feign;


import com.cpms.common.constant.AppConstant;
import com.cpms.framework.common.core.api.Result;
import com.cpms.system.api.modules.sys.dto.SysUserLginDTO;
import com.cpms.system.api.modules.sys.feign.factory.SysUserClientFallbackFactory;
import com.cpms.system.api.modules.sys.vo.SysUserLoginVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



/**
 * @author gulang
 * @Description:  微服务之间调用的系统用户相关接口
 */
@FeignClient(
        value = AppConstant.APPLICATION_SYSTEM_NAME,
        fallbackFactory = SysUserClientFallbackFactory.class,
        path = AppConstant.SYSTEM_API_PREFIX  // 接口前缀
)
public interface ISysUserClient {
    /**
     * api前缀
     */
     String API_PREFIX = "/sys-user-client";

    /**
     *  系统后台用户登录
     * @param sysUserLginDTO
     * @return
     */
     @PostMapping(API_PREFIX+"/login")
     Result<SysUserLoginVO> sysUserLogin(@RequestBody SysUserLginDTO sysUserLginDTO);
}
