package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.system.modules.sys.service.ISysMenuService;
import com.cpms.system.modules.sys.service.ISysTopMenuService;
import com.cpms.system.modules.sys.vo.SysRouteVO;
import com.cpms.system.modules.sys.vo.SysTopMenuVO;
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




}
