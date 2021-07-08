package com.cpms.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/20 18:04
 */

@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args)
    {
        try {
            SpringApplication.run(GatewayApplication.class, args);
            System.out.println("(♥◠‿◠)ﾉﾞ  Gateway服务启动成功   ლ(´ڡ`ლ)ﾞ");
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
