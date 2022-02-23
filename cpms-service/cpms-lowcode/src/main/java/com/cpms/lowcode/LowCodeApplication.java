package com.cpms.lowcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @description:
 * @author: gulang
 * @time: 2021/5/19 19:54
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LowCodeApplication {
    public static void main(String[] args)
    {
        try {
            SpringApplication.run(LowCodeApplication.class, args);
            System.out.println("(♥◠‿◠)ﾉﾞ  LowCode服务启动成功   ლ(´ڡ`ლ)ﾞ");
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
