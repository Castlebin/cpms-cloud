package com.cpms.system.modouls.sysUser.feign;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.system.api.bo.SysUserLoginBO;
import com.cpms.system.api.dto.SysUserLginDTO;
import com.cpms.system.api.feign.ISysUserClient;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gulang
 * @Description:
 */
@RestController
public class SysUserClient implements ISysUserClient {
    @Override
    @PostMapping("/sysUserLogin")
    public Result<SysUserLoginBO> sysUserLogin(SysUserLginDTO sysUserLginDTO) {
        SysUserLoginBO sysUserLoginBO = new SysUserLoginBO();
        sysUserLoginBO.setUserName(sysUserLginDTO.getUserName());
        sysUserLoginBO.setAccount("sysSuperAdmin");
        sysUserLoginBO.setPermissions(Lists.newArrayList("add","delete","edit"));
        sysUserLoginBO.setRoles(Lists.newArrayList("admin","market"));

//        int a = 1/0;
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return ResultUtil.success(sysUserLoginBO);
    }
}
