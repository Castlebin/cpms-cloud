package com.cpms.system;

import com.cpms.common.constant.AppConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/19 19:54
 */

@EnableTransactionManagement // 开启事务
@EnableFeignClients(AppConstant.BASE_PACKAGES)
@SpringCloudApplication
public class SystemApplication {
    public static void main(String[] args)
    {
        try {
            SpringApplication.run(SystemApplication.class, args);
            System.out.println("(♥◠‿◠)ﾉﾞ  System服务启动成功   ლ(´ڡ`ლ)ﾞ");
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
