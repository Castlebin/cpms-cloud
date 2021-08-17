package com.cpms.system.modules.sys.controller;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.system.modules.sys.service.SysMenuService;
import com.cpms.system.modules.sys.vo.SysMenuVO;
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
    private SysMenuService sysMenuService;

    /**
     *  获取菜单路由
     * @param topMenuId
     * @return
     */
    @GetMapping("/routes")
    public Result<List<SysMenuVO>> querySysMenuRoutes(@RequestParam(name = "topMenuId",required=false) Long topMenuId){
        return ResultUtil.success(sysMenuService.querySysMenuRoutes(topMenuId));
    }

    @GetMapping("/buttons")
    public Result<List<String>> queryRoleButtons(){
        return ResultUtil.success(sysMenuService.queryRoleButtons());
    }
}
