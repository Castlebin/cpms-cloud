package com.cpms.log;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

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

    // 设置@Value注解取值不到忽略（不报错）
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }
}
