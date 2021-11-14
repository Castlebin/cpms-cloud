package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.log.annotations.OperLog;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.OtherGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.QueryTenantDTO;
import com.cpms.system.modules.sys.dto.SysTenantDTO;
import com.cpms.system.modules.sys.service.ISysTenantService;
import com.cpms.system.modules.sys.vo.InitTenantAccountVO;
import com.cpms.system.modules.sys.vo.SysTenantVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
     *  租户下拉选择列表
     * @return
     */
    @GetMapping("/dropDownTenants")
    public Result<List<SysTenantVO>> dropDownTenants(){
        return ResultUtil.success(sysTenantService.dropDownTenants());
    }

    /**
     *  租户分页列表
     * @param listTenantDTO
     * @return
     */
    @PostMapping("/list")
    @PreAuth("sys_tenant_view")
    public Result<BasePageVO<SysTenantVO>> listTenant(@RequestBody QueryTenantDTO listTenantDTO){
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
    @PreAuth("sys_tenant_edit")
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

    /**
     *  配置租户菜单权限
     * @param tenantDTO
     * @return
     */
    @PostMapping("/configTenantPer")
    @PreAuth("sys_tenant_config_per")
    @OperLog(desc = "配置租户菜单权限")
    public Result<Void> configTenantPer(@Validated(OtherGroup.class)@RequestBody SysTenantDTO tenantDTO){
        return ResultUtil.status(sysTenantService.configTenantPer(tenantDTO));
    }

    /**
     *  改变租户状态
     * @param tenantId
     * @param tenantStatus
     * @return
     */
    @GetMapping("/changeTenantStatus")
    @PreAuth("sys_tenant_edit")
    @OperLog(desc = "改变租户状态")
    public Result<Void> changeTenantStatus(
            @RequestParam(name="tenantId")  Long tenantId,
            @RequestParam(name="tenantStatus")  Integer tenantStatus
    ){
        return ResultUtil.status(sysTenantService.changeTenantStatus(tenantId,tenantStatus));
    }

}
