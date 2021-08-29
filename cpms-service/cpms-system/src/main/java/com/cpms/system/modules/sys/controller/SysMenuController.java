package com.cpms.system.modules.sys.controller;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.framework.redis.utils.CsRedissonUtil;
import com.cpms.system.modules.sys.service.ISysMenuService;
import com.cpms.system.modules.sys.vo.SysRouteVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    /**
     *  获取菜单路由
     * @param topMenuId
     * @return
     */
    @GetMapping("/getMenu")
    public Result<SysRouteVO> querySysMenuRoutes(@RequestParam(name = "topMenuId",required=false) Long topMenuId){
        return ResultUtil.success(sysMenuService.querySysMenuRoutes(topMenuId));
    }

    /**
     * redisson锁测试
     * @return
     */
    @GetMapping("/redissonLock")
    public Result<Void> redissonLock(){
        if(!CsRedissonUtil.isLocked("test-lock")) {
            CsRedissonUtil.lock("test-lock");
        }else{
            return ResultUtil.error(123,"锁已被占用！！！");
        }
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultUtil.success();
    }
}
