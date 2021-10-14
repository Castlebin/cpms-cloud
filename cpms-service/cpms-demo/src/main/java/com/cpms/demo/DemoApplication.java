package com.cpms.demo;

import com.cpms.common.constant.AppConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/19 19:54
 */

@MapperScan("com.cpms.**.mapper") //扫描mapper包 避免在每个mapper类上加@Mapper注解
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@SpringCloudApplication
public class DemoApplication {
    public static void main(String[] args)
    {
        try {
            SpringApplication.run(DemoApplication.class, args);
            System.out.println("(♥◠‿◠)ﾉﾞ  Demo服务启动成功   ლ(´ڡ`ლ)ﾞ");
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
