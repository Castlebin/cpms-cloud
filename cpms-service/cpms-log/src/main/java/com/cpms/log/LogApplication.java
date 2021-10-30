package com.cpms.log;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/19 19:47
 */
@SpringCloudApplication
public class LogApplication {
    public static void main(String[] args)
    {
        try {
            SpringApplication.run(LogApplication.class, args);
            System.out.println("(♥◠‿◠)ﾉﾞ  Log服务启动成功   ლ(´ڡ`ლ)ﾞ");
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
