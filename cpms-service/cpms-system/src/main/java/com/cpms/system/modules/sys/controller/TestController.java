package com.cpms.system.modules.sys.controller;

import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.utils.thread.ThreadPoolBuilder;
import com.cpms.framework.redis.utils.CsRedissonUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
            System.out.println("------a="+a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            CsRedissonUtil.unlock("redisson-lock");
        }
        return ResultUtil.success(a);
    }

    /**
     *  线程池测试
     * @return
     */
    @GetMapping("/thread")
    public Result<Void> thread(){
        ThreadPoolTaskExecutor executor = ThreadPoolBuilder.buildThreadPool(32);
        executor.execute(()-> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("测试------"+Thread.currentThread().getName());
        });
        return ResultUtil.success();
    }
}
