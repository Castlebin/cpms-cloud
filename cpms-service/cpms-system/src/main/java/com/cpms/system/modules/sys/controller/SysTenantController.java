package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.log.annotations.OperLog;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.ListTenantDTO;
import com.cpms.system.modules.sys.dto.SysTenantDTO;
import com.cpms.system.modules.sys.service.ISysTenantService;
import com.cpms.system.modules.sys.vo.InitTenantAccountVO;
import com.cpms.system.modules.sys.vo.SysTenantVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/22 11:30
 */
@RestController
@RequestMapping("/sys-tenant")
public class SysTenantController {
    @Resource
    private ISysTenantService sysTenantService;

    /**
     *  租户列表
     * @return
     */
    @GetMapping("/tenants")
    public Result<List<SysTenantVO>> tenants(){
        return ResultUtil.success(sysTenantService.tenants());
    }

    /**
     *  租户列表
     * @param listTenantDTO
     * @return
     */
    @PostMapping("/list")
    @PreAuth("sys_tenant_list")
    public Result<BasePageVO<SysTenantVO>> listTenant(@RequestBody ListTenantDTO listTenantDTO){
        return ResultUtil.success(sysTenantService.listTenant(listTenantDTO));
    }


    /**
     *  添加租户
     * @param tenantDTO
     * @return
     */
    @PostMapping("/add")
    @PreAuth("sys_tenant_add")
    @OperLog(desc = "新增租户")
    public Result<InitTenantAccountVO> addTenant(@Validated(AddGroup.class)@RequestBody SysTenantDTO tenantDTO){
        return ResultUtil.success(sysTenantService.addTenant(tenantDTO));
    }

    /**
     *  修改租户
     * @param tenantDTO
     * @return
     */
    @PostMapping("/update")
    @PreAuth("sys_tenant_update")
    @OperLog(desc = "修改租户")
    public Result<Void> updateTenant(@Validated(UpdateGroup.class)@RequestBody SysTenantDTO tenantDTO){
        return ResultUtil.status(sysTenantService.updateTenant(tenantDTO));
    }

    /**
     *  修改租户
     * @param tenantDTO
     * @return
     */
    @PostMapping("/delete")
    @PreAuth("sys_tenant_delete")
    @OperLog(desc = "删除租户")
    public Result<Void> deleteTenant(@Validated(DeleteGroup.class)@RequestBody SysTenantDTO tenantDTO){
        return ResultUtil.status(sysTenantService.deleteTenant(tenantDTO));
    }
}
