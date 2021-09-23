package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.SysTopMenuDTO;
import com.cpms.system.modules.sys.service.ISysTopMenuService;
import com.cpms.system.modules.sys.vo.SysTopMenuVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/6 15:13
 */
@RestController
@RequestMapping("/sys-top-menu")
public class SysTopMenuController {
    @Resource
    private ISysTopMenuService sysTopMenuService;

    /**
     *  获取顶部菜单路由
     * @return
     */
    @PostMapping("/list")
    public Result<List<SysTopMenuVO>> listTopMenu(){
        return ResultUtil.success(sysTopMenuService.listTopMenu());
    }

    /**
     *  获取顶部菜单路由
     * @return
     */
    @PostMapping("/top-menu")
    public Result<List<SysTopMenuVO>> getTopMenu(){
        return ResultUtil.success(sysTopMenuService.getTopMenu());
    }

    /**
     *  添加菜单
     * @return
     */
    @PostMapping("/add")
    @PreAuth("sys_top_menu_add")
    public Result<Void> addTopMenu(@Validated(AddGroup.class) @RequestBody SysTopMenuDTO sysTopMenuDTO){
        return ResultUtil.status(sysTopMenuService.addTopMenu(sysTopMenuDTO));
    }

    /**
     *  更新操作
     * @param sysTopMenuDTO
     * @return
     */
    @PostMapping("/update")
    @PreAuth("sys_top_menu_update")
    public Result<Void> updateTopMenu(@Validated(UpdateGroup.class) @RequestBody SysTopMenuDTO sysTopMenuDTO){
        return ResultUtil.status(sysTopMenuService.updateTopMenu(sysTopMenuDTO));
    }

    /**
     *  删除操作
     * @param sysTopMenuDTO
     * @return
     */
    @PostMapping("/delete")
    @PreAuth("sys_top_menu_delete")
    public Result<Void> deleteTopMenu(@Validated(DeleteGroup.class) @RequestBody SysTopMenuDTO sysTopMenuDTO){
        return ResultUtil.status(sysTopMenuService.deleteTopMenu(sysTopMenuDTO));
    }

}
