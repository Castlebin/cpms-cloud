package com.cpms.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author gulang
 * @Description:  访问地址：http://localhost:5003/monitorService   账号/密码：monitor/monitor
 * @time: 2022/1/6 20:04
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer // 开启AdminServer的功能
public class MonitorAdminApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(MonitorAdminApplication.class, args);
            System.out.println("(♥◠‿◠)ﾉﾞ  Monitor服务启动成功   ლ(´ڡ`ლ)ﾞ");
        }catch(Throwable e) {
            e.printStackTrace();
        }
    }

}
