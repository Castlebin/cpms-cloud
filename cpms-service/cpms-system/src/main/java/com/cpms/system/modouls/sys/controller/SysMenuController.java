package com.cpms.system.modouls.sys.controller;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.framework.security.annotations.PreAuth;
import com.cpms.system.modouls.sys.service.SysMenuService;
import com.cpms.system.modouls.sys.vo.SysMenuVO;
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
