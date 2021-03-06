 <p align="center">
  <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR5-green.svg" alt="Downloads">
  <img src="https://img.shields.io/badge/SpringBoot-2.2.7.RELEASE-brightgreen.svg" alt="Build Status">
  <img src="https://img.shields.io/badge/spring%20cloud%20alibaba-2.2.1.RELEASE-red.svg" alt="Build Status">
  <img src="https://img.shields.io/badge/JDK-11-informational.svg" alt="Coverage Status">
  <img src="https://img.shields.io/badge/mysql-5.7+-informational.svg" alt="Downloads">
  <img src="https://img.shields.io/badge/mybatis%20plus-3.4.3-yellow.svg" alt="Downloads">
  <img src="https://img.shields.io/badge/redission-3.16.0+-important.svg" alt="Downloads">
   <img src="https://img.shields.io/badge/openfeign-2.2.3.RELEASE-yellowgreen.svg" alt="Downloads">
 <img src="https://img.shields.io/badge/xxl%20job-2.3.0-blue.svg" alt="Downloads">
<img src="https://img.shields.io/badge/lombok-1.18.12-green.svg" alt="Downloads">
<img src="https://img.shields.io/badge/fastjson-1.2.75-green.svg" alt="Downloads">
<img src="https://img.shields.io/badge/guava-30.1.1%20jre-informational.svg" alt="Downloads">

<a target="_blank" href="https://gitee.com/gldcty/cpms-core-framework">
  <img src="https://img.shields.io/badge/cpms%20core%20framework-1.0.0-blue.svg" alt="Downloads">
 </a>

 <a target="_blank" href="https://www.cpms.vip">
   <img src="https://img.shields.io/badge/Copyright-@cpms-success.svg" alt="Downloads">
 </a>
 </p>  

#### 介绍

> 项目名：cpms 是Concise practical management system 的首字母缩写，意思是：简洁实用的后台管理系统

cpms-cloud是基于微服务框架spring-cloud-alibaba搭建的一套多租户后台管理系统脚手架，采用前后端分离架构，前端使用vue2.0和element-ui2.0开发。

该脚手架是一套通用且可快速进行二次开发的后台微服务框架。已经集成了管理系统最基础的后台模块，包括：菜单管理、用户管理、租户管理、角色管理、部门管理、权限管理以及后台操作日志管理等模块。

后续会不断添加新的功能模块~~~~~~

#### 部署架构图
 <img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/cpms-cloud.drawio.png">

#### 工程目录结构

```
cpms-cloud
├── cpms-auth -- 认证授权服务
├── cpms-common -- 常用公共模块
├── cpms-gateway -- Spring Cloud gateway 网关层
├── cpms-service -- 微服务业务模块
    ├    ├── cpms-log -- 日志模块，
    ├    ├── cpms-lowcode -- 低代码生成器模块，
    └──  └── cpms-system -- 系统模块（包含：菜单、用户、权限管理等模块）
├── cpms-service-api -- 微服务通过feign相互调用api模块
    ├    ├── cpms-system-api -- 系统api 
    └──  └── cpms-log-api -- 日志api 
├── cpms-visual -- 控制台视图层
    ├    ├── cpms-monitor-admin -- spring-boot-admin监控管理
    ├    ├── cpms-sentinel-dashboard -- sentinel-dashboard 控制台管理
    ├    ├── cpms-job-admin -- xxl-job-admin 控制台管理
    
```

#### 功能模块

1. 用户管理：用户是租户管理员，维护租户管理员信息

2. 部门管理：配置租户组织机构（公司、部门、小组）

3. 岗位管理：配置租户用户所属担任职务。

4. 菜单管理：配置系统菜单，操作权限，按钮权限标识等。

5. 角色管理：角色菜单权限分配

6. 操作日志：系统操作日志记录和查询

7. 顶部菜单：个性化配置，将常用功能模块归类

8. 租户管理：添加租户信息，并生成租户管理员账号信息，添加租户权限

9. 开发工具：代码生成器，生成项目脚手架和表增删改查业务代码
10. 持续更新新模块......


#### 前端UI
基于vue2.0+elementui: [https://gitee.com/gldcty/cpms-cloud-vue-ui](https://gitee.com/gldcty/cpms-cloud-vue-ui) 

#### 在线演示
  演示地址：[https://www.cpms.vip/](https://www.cpms.vip/)

#### 相关项目

cpms单体架构：https://gitee.com/gldcty/cpms

cpms-core-framework核心组件库:https://gitee.com/gldcty/cpms-core-framework

#### 后端技术栈

| 框架                       | 版本号        | 描述                                                         |
| -------------------------- | ------------- | ------------------------------------------------------------ |
| spring-cloud               | Hoxton.SR5    |                                                              |
| spring-cloud-alibaba       | 2.2.1.RELEASE |                                                              |
| spring-boot                | 2.2.7.RELEASE |                                                              |
| openfeign                  | 2.2.3.RELEASE | 微服务接口调用组件                                           |
| hystrix                    | 2.2.3.RELEASE | 熔断降级组件                                                 |
| sentinel                   | 2.2.1.RELEASE | sentinel 熔断限流组件                                               |
| lombok                     | 1.18.12       |                                                              |
| mybatis-plus               | 3.4.3         |                                                              |
| dynamic-datasource         | 3.3.2         | 多数据源组件                                                 |
| fastjson                   | 1.2.75        |                                                              |
| redisson                   | 3.16.0        | redisson实现分布式锁                                         |
| guava                      | 30.1.1-jre    |                                                              |
| jjwt                       | 0.9.1         | 生成jwt toke认证                                             |
| jasypt-spring-boot-starter | 2.1.0         | 加密组件                                                     |
| easyexcel                  | 3.0.3         |                                                              |
| spring-security-crypto     | 5.5.2         | 用户密码加密组件库                                           |
| commons-lang3              | 3.12.0        |                                                              |
| spring-boot-starter-log4j2 | 2.2.7.RELEASE |                                                              |
| mysql-connector            | 8.0.20        |                                                              |
| cpms-core-framework        | 1.0.0         | cpms项目封装的核心组件库<br />https://gitee.com/gldcty/cpms-core-framework |
| .........                  |               |                                                              |



#### 前端技术栈


| 框架       | 版本   | 描述                   |
| ---------- | ------ | ---------------------- |
| Avue       | 2.0.0  | 基于avue基础layout布局 |
| vue        | 2.5.16 | vue全家桶              |
| element-ui | 2.15.6 |                        |
| ......     |        |                        |


#### QQ交流群
<img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/qq-qun-01.png"/>


#### 后台界面预览图

<table>
        <tr>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic12.png"/></td>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic11.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic10.png"/></td>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic09.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic08.png"/></td>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic07.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic06.png"/></td>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic05.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic04.png"/></td>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic03.png"/></td>
    </tr>
    <tr>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic01.png"/></td>
        <td><img src="https://gitee.com/gldcty/cpms-cloud/raw/master/doc/img/pic02.png"/></td>
    </tr>
</table>















