package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.core.base.BasePageVO;
import com.cpms.framework.log.annotations.OperLog;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.ListRoleDTO;
import com.cpms.system.modules.sys.dto.SysRoleDTO;
import com.cpms.system.modules.sys.service.ISysRoleService;
import com.cpms.system.modules.sys.vo.SysRoleVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 15:13
 */
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {
    @Resource
    private ISysRoleService sysRoleService;


    /**
     *  获取角色列表
     * @param listRoleDTO
     * @return
     */
    @PostMapping("/list")
    @PreAuth("sys_role_list")
    @OperLog(desc = "角色列表")
    public Result<BasePageVO<SysRoleVO>> listRole(@RequestBody ListRoleDTO listRoleDTO){
        return ResultUtil.success(sysRoleService.listRole(listRoleDTO));
    }

    /**
     *  添加菜单
     * @param sysRoleDTO
     * @return
     */
    @PostMapping("/addRole")
    @PreAuth("sys_role_add")
    @OperLog(desc = "添加角色")
    public Result<Void> addRole(@Validated(AddGroup.class) @RequestBody SysRoleDTO sysRoleDTO){
        return ResultUtil.status(sysRoleService.addRole(sysRoleDTO));
    }

    /**
     *  更新操作
     * @param sysRoleDTO
     * @return
     */
    @PostMapping("/updateRole")
    @PreAuth("sys_role_update")
    @OperLog(desc = "更新角色")
    public Result<Void> updateRole(@Validated(UpdateGroup.class) @RequestBody SysRoleDTO sysRoleDTO){
        return ResultUtil.status(sysRoleService.updateRole(sysRoleDTO));
    }

    /**
     *  删除操作
     * @param sysRoleDTO
     * @return
     */
    @PostMapping("/deleteRole")
    @PreAuth("sys_role_delete")
    @OperLog(desc = "删除角色")
    public Result<Void> deleteRole(@Validated(DeleteGroup.class) @RequestBody SysRoleDTO sysRoleDTO){
        return ResultUtil.status(sysRoleService.deleteRole(sysRoleDTO));
    }

}
