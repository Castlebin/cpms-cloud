package com.cpms.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/19 19:47
 */
@EnableTransactionManagement // 开启事务
@SpringCloudApplication
public class LogApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(LogApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Log服务启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
