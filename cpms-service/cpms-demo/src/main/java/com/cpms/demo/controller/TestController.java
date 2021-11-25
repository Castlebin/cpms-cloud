package com.cpms.demo.controller;

import com.cpms.demo.dto.TestDTO;
import com.cpms.framework.common.core.api.Result;
import com.cpms.framework.common.core.api.ResultUtil;
import com.cpms.framework.common.utils.CsFileUtil;
import com.cpms.framework.common.utils.thread.ThreadPoolBuilder;
import com.cpms.framework.redis.annotations.DistributedLock;
import com.cpms.framework.redis.annotations.RepeatSubmit;
import com.cpms.framework.redis.utils.CsRedissonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.concurrent.TimeUnit;


/**
 * @description:
 * @author: gulang
 * @time: 2021/8/31 11:27
 */
@RestController
@RequestMapping("/demo-test")
@Slf4j
public class TestController {
    private static  int a= 0;

    /**
     *  分布式锁
     * @return
     */
    @GetMapping("/lock")
    @DistributedLock(value ="userName",prefixKey = "test",waitTime = 2)
    public Result<Integer> lock() throws InterruptedException {
        // 已设置锁，其它线程会被阻塞，底层实现可重入锁
        String key = "redisson-lock";
//        if(CsRedissonUtil.isLocked(key)) {
//            return ResultUtil.error(1000,"稍后再试");
//        }
//        CsRedissonUtil.lock(key);
//
//        if(!CsRedissonUtil.tryLock(key,TimeUnit.SECONDS,5)) {
//            return ResultUtil.error(1000,"稍后再试");
//        }
//        try {
//            TimeUnit.SECONDS.sleep(60);
//            a++;
//            System.out.println("------a="+a);
//        } finally {
//            System.out.println("释放锁....");
//            CsRedissonUtil.unlock("redisson-lock");
//        }
        System.out.println("执行业务方法.....");
        return ResultUtil.success();
    }

    /**
     *  线程池测试
     * @return
     */
    @GetMapping("/threadPool")
    public Result<Void> threadPool(HttpServletRequest request){
        log.info("main线程测试------"+Thread.currentThread().getName());
        ThreadPoolTaskExecutor executor = ThreadPoolBuilder.buildThreadPool(32);
        executor.execute(()-> {
            log.info("子线程测试------"+Thread.currentThread().getName());
        });
        return ResultUtil.success();
    }

    /**
     *  下载文件
     * @return
     */
    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response){
        String fileName = "temp.xlsx";
        String fileDir = "staticfile";
        CsFileUtil.downLocalFile(response, fileDir, fileName);
    }

    /**
     *  上传文件
     * @return
     */
    @PostMapping("/uploadFile")
    public void uploadFile(MultipartFile uploadFile){
        CsFileUtil.saveFileUpload("img","8888",uploadFile);
    }

    /**
     *  防重提交
     * @return
     */
    @PostMapping("/repeatSubmit")
    @RepeatSubmit(expire = 10,fields = {"userId","userName"},delLock = false)
    public Result<Boolean> repeatSubmit(@RequestBody TestDTO testDTO) {
        System.out.println("执行方法业务....");
        return ResultUtil.success();
    }
}
