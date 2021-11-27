#### 介绍
项目名：cpms 是Concise practical management system 的首字母缩写，意思是：简洁实用的后台管理系统

cpms-cloud是基于微服务框架spring-cloud-alibaba搭建的一套多租户后台管理系统脚手架，采用前后端分离架构。

该脚手架是一套通用且可快速进行二次开发的后台微服务框架。已经集成了管理系统最基础的后台模块，包括：菜单管理、用户管理、租户管理、角色管理、部门管理、权限管理以及后台操作日志管理等模块。

#### 项目技术栈

| 框架                       | 版本号        | 描述                                                         |
| -------------------------- | ------------- | ------------------------------------------------------------ |
| spring-cloud               | Hoxton.SR5    |                                                              |
| spring-cloud-alibaba       | 2.2.1.RELEASE |                                                              |
| spring-boot                | 2.2.7.RELEASE |                                                              |
| lombok                     | 1.18.12       |                                                              |
| mybatis-plus               | 3.4.3         |                                                              |
| dynamic-datasource         | 3.3.2         | 多数据源组件                                                 |
| fastjson                   | 1.2.75        |                                                              |
| redisson                   | 3.16.0        | redisson实现分布式锁                                         |
| guava                      | 30.1.1-jre    |                                                              |
| jjwt                       | 0.9.1         | 生成jwt toke认证                                             |
| jasypt                     | 2.1.0         | 加密组件                                                     |
| easyexcel                  | 3.0.3         |                                                              |
| spring-security-crypto     | 5.5.2         | 用户密码加密组件库                                           |
| commons-lang3              | 3.12.0        |                                                              |
| spring-boot-starter-log4j2 | 2.2.7.RELEASE |                                                              |
| mysql-connector            | 8.0.20        |                                                              |
| cpms-core-framework        | 1.0.0         | cpms项目封装的核心组件库<br />https://gitee.com/gldcty/cpms-core-framework |
| .........                  |               |                                                              |



#### 工程目录结构

```
​``` 
cpms-cloud
├── cpms-auth -- 认证授权服务
├── cpms-common -- 常用公共模块
├── cpms-gateway -- Spring Cloud gateway 网关层
├── cpms-service -- 微服务业务模块
    ├    ├── cpms-log -- 日志模块 
    └──  └── cpms-system -- 系统模块 
├── cpms-service-api -- 微服务通过feign相互调用api模块
    ├    ├── cpms-system-api -- 系统api 
└── └──  └── cpms-log-api -- 日志api 
​```
```



#### 后台界面预览图

















