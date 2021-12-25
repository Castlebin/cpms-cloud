package com.alibaba.csp.sentinel.dashboard;

import com.alibaba.csp.sentinel.init.InitExecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 访问地址:http://localhost:端口/sentinel-dashboard  默认账号/密码：sentinel/sentinel
 * @author cpms
 */
@SpringBootApplication
public class DashboardApplication {

    public static void main(String[] args) {
        triggerSentinelInit();
        SpringApplication.run(DashboardApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  sentinel-dashboard服务启动成功   ლ(´ڡ`ლ)ﾞ");
    }

    private static void triggerSentinelInit() {
        new Thread(() -> InitExecutor.doInit()).start();
    }
}
