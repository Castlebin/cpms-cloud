package com.cpms.system;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @description:
 * @author: 01396003
 * @time: 2021/5/19 19:54
 */
@SpringCloudApplication
public class SystemApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(SystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  System服务启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
