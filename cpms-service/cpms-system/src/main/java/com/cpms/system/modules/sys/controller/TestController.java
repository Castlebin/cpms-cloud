package com.cpms.system.modules.sys.controller;

import com.cpms.common.core.api.Result;
import com.cpms.common.core.api.ResultUtil;
import com.cpms.framework.redis.utils.CsRedissonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: gulang
 * @time: 2021/8/31 11:27
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static  int a= 0;

    /**
     *  分布式锁
     * @return
     */
    @GetMapping("/lock")
    public Result<Integer> lock(){
        // 已设置锁，其它线程会被阻塞，底层实现可重入锁
        CsRedissonUtil.lock("redisson-lock");
        try {
            TimeUnit.SECONDS.sleep(4);
            a++;
            System.out.println("=============a=="+a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            CsRedissonUtil.unlock("redisson-lock");
        }
        return ResultUtil.success(a);
    }
}
