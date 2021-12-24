package com.xxl.job.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后台访问地址：http://localhost:端口/xxl-job-admin  默认账号密码：admin/123456
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
public class XxlJobAdminApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(XxlJobAdminApplication.class, args);
			System.out.println("(♥◠‿◠)ﾉﾞ  xxl-job服务启动成功   ლ(´ڡ`ლ)ﾞ");
		}catch(Throwable e) {
			e.printStackTrace();
		}

	}

}