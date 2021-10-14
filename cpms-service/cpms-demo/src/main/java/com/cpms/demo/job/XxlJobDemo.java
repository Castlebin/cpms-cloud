package com.cpms.demo.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: gulang
 * @time: 2021/10/8 16:43
 */
@Slf4j
@Component
public class XxlJobDemo {
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        System.out.println("param:"+param);
        log.info("XXL-JOB, Hello World.");
        return ReturnT.SUCCESS;
    }
}
