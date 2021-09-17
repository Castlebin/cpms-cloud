package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.mybatis.groups.AddGroup;
import com.cpms.framework.mybatis.groups.DeleteGroup;
import com.cpms.framework.mybatis.groups.UpdateGroup;
import com.cpms.framework.secure.annotations.PreAuth;
import com.cpms.system.modules.sys.dto.SysMenuDTO;
import com.cpms.system.modules.sys.service.ISysMenuService;
import com.cpms.system.modules.sys.service.ISysTopMenuService;
import com.cpms.system.modules.sys.vo.SysRouteVO;
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
@RequestMapping("/sys-menu")
public class SysMenuController {
    @Resource
    private ISysMenuService sysMenuService;
    @Resource
    private ISysTopMenuService sysTopMenuService;

    /**
     *  获取左侧菜单路由
     * @param topMenuId
     * @return
     */
    @GetMapping("/getMenu")
    public Result<SysRouteVO> querySysMenuRoutes(@RequestParam(name = "topMenuId",required=false) Long topMenuId){
        return ResultUtil.success(sysMenuService.querySysMenuRoutes(topMenuId));
    }

    /**
     *  获取顶部菜单路由
     * @return
     */
    @GetMapping("/top-menu")
    public Result<List<SysTopMenuVO>> getTopMenu(){
        return ResultUtil.success(sysTopMenuService.getTopMenu());
    }

    /**
     *  添加菜单
     * @return
     */
    @PostMapping("/addMenu")
    @PreAuth("sys_menu_add")
    public Result<Void> addMenu(@Validated(AddGroup.class) @RequestBody SysMenuDTO sysMenuDTO){
        return ResultUtil.status(sysMenuService.addMenu(sysMenuDTO));
    }

    /**
     *  更新操作
     * @param sysMenuDTO
     * @return
     */
    @PostMapping("/updateMenu")
    @PreAuth("sys_menu_update")
    public Result<Void> updateMenu(@Validated(UpdateGroup.class) @RequestBody SysMenuDTO sysMenuDTO){
        return ResultUtil.status(sysMenuService.updateMenu(sysMenuDTO));
    }

    /**
     *  删除操作
     * @param sysMenuDTO
     * @return
     */
    @PostMapping("/deleteMenu")
    @PreAuth("sys_menu_delete")
    public Result<Void> deleteMenu(@Validated(DeleteGroup.class) @RequestBody SysMenuDTO sysMenuDTO){
        return ResultUtil.status(sysMenuService.deleteMenu(sysMenuDTO));
    }

}
