package com.cpms.auth;

import com.cpms.common.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/20 18:53
 */
// 扫描和注册feign客户端bean定义，即使用@FeignClient注解的接口
@EnableFeignClients(AppConstant.BASE_PACKAGES)
// springcloud集合注解
@SpringCloudApplication
public class AuthApplication {
    public static void main(String[] args)
    {
        try {
            SpringApplication.run(AuthApplication.class, args);
            System.out.println("(♥◠‿◠)ﾉﾞ  Auth服务启动成功   ლ(´ڡ`ლ)ﾞ");
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }

    // 设置@Value注解取值不到忽略（不报错）
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }
}
