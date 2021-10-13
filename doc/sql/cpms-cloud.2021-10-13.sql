/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.26 : Database - cpms-cloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cpms-cloud` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `cpms-cloud`;

/*Table structure for table `cpms_system_dept` */

DROP TABLE IF EXISTS `cpms_system_dept`;

CREATE TABLE `cpms_system_dept` (
  `dept_id` bigint(64) unsigned NOT NULL COMMENT '部门ID',
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `dept_name` varchar(50) NOT NULL COMMENT '部门名称',
  `dept_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '部门描述',
  `parent_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '树形结构，父节点id',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `cpms_system_dept` */

insert  into `cpms_system_dept`(`dept_id`,`tenant_id`,`dept_name`,`dept_desc`,`parent_id`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,'深圳巨人科技软件服务有限公司总部','安全稳定人 前端开发小组前端开发小组前端开发小组前端开发小组前端开发小组前端开发小组',0,0,'2021-06-13 16:13:50','2021-10-12 19:27:50','CS888888','CS888888'),(2,1,'Cpms系统深圳研发部','前端开发小组',1,0,'2021-06-13 16:13:50','2021-10-12 19:27:44','CS888888','CS888888'),(3,2,'深圳梦想科技有限公司总部','',0,0,'2021-06-13 16:13:50','2021-10-10 19:20:19','CS888888','CS888888'),(4,1,'前端研发组','前端开发小组',2,0,'2021-10-10 19:19:06','2021-10-12 19:25:27','CS888888','CS888888'),(5,1,'后端研发组','后端开发小组',2,0,'2021-10-10 19:19:34','2021-10-12 19:25:39','CS888888','CS888888'),(1442414898812084225,1442414898631729154,'深圳爱美造型时尚有限公司总部','',0,0,'2021-09-27 17:04:45','2021-10-10 19:20:27','CS888888','CS888888'),(1442429609511301122,1442429609494523905,'深圳美容资讯有限公司总部','',0,0,'2021-09-27 18:03:13','2021-10-10 19:20:28','CS888888','CS888888'),(1442702604972666882,1442702604909752321,'深圳发型改造有限公司总部','',0,0,'2021-09-28 12:08:00','2021-10-10 19:20:33','CS888888','CS888888'),(1444119091383648258,1444119091375259650,'23总部','',0,0,'2021-10-02 09:56:36','2021-10-03 10:34:36','CS888888','CS888888'),(1444120246532718594,1444120246532718593,'231e4总部','',0,0,'2021-10-02 10:01:12','2021-10-03 10:34:37','CS888888','CS888888'),(1444133691953004546,1444133691953004545,'123总部','',0,0,'2021-10-02 10:54:38','2021-10-03 10:34:39','CS888888','CS888888'),(1444133790506565634,1444133790506565633,'QWE总部','',0,0,'2021-10-02 10:55:01','2021-10-03 10:34:41','CS888888','CS888888'),(1444133949051256835,1444133949051256834,'QWE总部','',0,0,'2021-10-02 10:55:39','2021-10-03 10:34:43','CS888888','CS888888'),(1444134122494115841,1444134122431201281,'SDFDS总部','',0,0,'2021-10-02 10:56:20','2021-10-03 10:34:48','CS888888','CS888888');

/*Table structure for table `cpms_system_log` */

DROP TABLE IF EXISTS `cpms_system_log`;

CREATE TABLE `cpms_system_log` (
  `log_id` bigint(64) NOT NULL COMMENT '日志ID',
  `tenant_id` bigint(64) NOT NULL COMMENT '租户ID',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_name` varchar(50) DEFAULT NULL COMMENT '服务ID',
  `handle_ip` varchar(255) DEFAULT NULL COMMENT '操作人IP地址',
  `req_url` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `req_method` varchar(10) DEFAULT NULL COMMENT '请求方式',
  `req_params` text COMMENT '操作提交的入参',
  `exe_time` int(11) DEFAULT NULL COMMENT '执行时间(毫秒)',
  `result_msg` text COMMENT '结果信息',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台系统操作日志表';

/*Data for the table `cpms_system_log` */

insert  into `cpms_system_log`(`log_id`,`tenant_id`,`title`,`service_name`,`handle_ip`,`req_url`,`req_method`,`req_params`,`exe_time`,`result_msg`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1444108594924068865,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳\",\"tenantName\":\"测试租户001\",\"tenantDesc\":\"\",\"contactNumber\":1314159,\"accountPrefix\":\"CS\",\"tenantCode\":\"ONE_ONE\",\"leaseTimeStart\":\"2021-10-02T01:14:16.000Z\",\"contacts\":\"老王\",\"leaseTimeEnd\":\"2021-10-02T01:14:19.000Z\"}',35,'{\"applicationName\":\"cpms-server-system\",\"code\":20012,\"date\":\"2021-10-02 09:14:51\",\"message\":\"参数体不能为空\",\"success\":false}',0,'CS888888','2021-10-02 09:14:54','CS888888','2021-10-02 09:14:54'),(1444108613735522305,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳\",\"tenantName\":\"测试租户001\",\"tenantDesc\":\"\",\"contactNumber\":1314159,\"accountPrefix\":\"CS\",\"tenantCode\":\"ONE_ONE\",\"leaseTimeStart\":\"2021-10-02T01:14:16.000Z\",\"contacts\":\"老王\",\"leaseTimeEnd\":\"2021-10-02T01:14:19.000Z\"}',2,'{\"applicationName\":\"cpms-server-system\",\"code\":20012,\"date\":\"2021-10-02 09:14:58\",\"message\":\"参数体不能为空\",\"success\":false}',0,'CS888888','2021-10-02 09:14:58','CS888888','2021-10-02 09:14:58'),(1444108852550803457,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳\",\"tenantName\":\"测试租户001\",\"tenantDesc\":\"\",\"contactNumber\":1314159,\"accountPrefix\":\"CS\",\"tenantCode\":\"ONE_ONE\",\"leaseTimeStart\":\"2021-10-02T01:14:16.000Z\",\"contacts\":\"老王\",\"leaseTimeEnd\":\"2021-10-02T01:14:19.000Z\"}',2,'{\"applicationName\":\"cpms-server-system\",\"code\":20012,\"date\":\"2021-10-02 09:15:55\",\"message\":\"参数体不能为空\",\"success\":false}',0,'CS888888','2021-10-02 09:15:55','CS888888','2021-10-02 09:15:55'),(1444112437917360130,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"32\",\"tenantName\":\"32\",\"tenantDesc\":\"DSF \",\"contactNumber\":2132132,\"accountPrefix\":\"SD\",\"tenantCode\":\"32\",\"leaseTimeStart\":\"2021-10-06T15:59:59.000Z\",\"contacts\":\"32\",\"leaseTimeEnd\":\"2021-10-15T15:59:59.000Z\"}',2,'{\"applicationName\":\"cpms-server-system\",\"code\":20012,\"date\":\"2021-10-02 09:30:10\",\"message\":\"参数体不能为空\",\"success\":false}',0,'CS888888','2021-10-02 09:30:10','CS888888','2021-10-02 09:30:10'),(1444112455579574274,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"32\",\"tenantName\":\"32\",\"tenantDesc\":\"DSF \",\"contactNumber\":2132132,\"accountPrefix\":\"SD\",\"tenantCode\":\"32\",\"leaseTimeStart\":\"2021-10-06T15:59:59.000Z\",\"contacts\":\"32\",\"leaseTimeEnd\":\"2021-10-15T15:59:59.000Z\"}',2,'{\"applicationName\":\"cpms-server-system\",\"code\":20012,\"date\":\"2021-10-02 09:30:14\",\"message\":\"参数体不能为空\",\"success\":false}',0,'CS888888','2021-10-02 09:30:14','CS888888','2021-10-02 09:30:14'),(1444112978479259650,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"234\",\"tenantName\":\"23\",\"tenantDesc\":\"\",\"contactNumber\":234,\"accountPrefix\":\"AS\",\"tenantCode\":\"234\",\"leaseTimeStart\":\"2021-10-13T15:59:59.000Z\",\"contacts\":\"324\",\"leaseTimeEnd\":\"2021-10-21T15:59:59.000Z\"}',1,'{\"applicationName\":\"cpms-server-system\",\"code\":20012,\"date\":\"2021-10-02 09:32:19\",\"message\":\"参数体不能为空\",\"success\":false}',0,'CS888888','2021-10-02 09:32:19','CS888888','2021-10-02 09:32:19'),(1444119092096770049,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"324\",\"tenantName\":\"23\",\"tenantDesc\":\"\",\"contactNumber\":324,\"accountPrefix\":\"as\",\"tenantCode\":\"23\",\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"234\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\"}',341,'{\"applicationName\":\"cpms-server-system\",\"code\":20000,\"date\":\"2021-10-02 09:56:36\",\"message\":\"响应成功\",\"success\":true}',0,'CS888888','2021-10-02 09:56:37','CS888888','2021-10-02 09:56:37'),(1444120247182925825,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"32432\",\"tenantName\":\"231e4\",\"tenantDesc\":\"\",\"contactNumber\":23432,\"accountPrefix\":\"sd\",\"tenantCode\":\"3242\",\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"234\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\"}',165,'{\"applicationName\":\"cpms-server-system\",\"code\":20000,\"date\":\"2021-10-02 10:01:12\",\"message\":\"响应成功\",\"success\":true}',0,'CS888888','2021-10-02 10:01:12','CS888888','2021-10-02 10:01:12'),(1444123429506228225,1,'修改租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/update','POST','{\"tenantStatus\":0,\"address\":\"324\",\"tenantDesc\":\"\",\"accountPrefix\":\"as\",\"updateTime\":\"2021-10-02 09:56:36\",\"tenantCode\":\"23\",\"leaseTimeEnd\":\"2021-10-27 23:59:59\",\"createBy\":\"CS888888\",\"tenantName\":\"23\",\"createTime\":\"2021-10-02 09:56:36\",\"updateBy\":\"CS888888\",\"tenantId\":1444119091375259600,\"contactNumber\":324,\"leaseTimeStart\":\"2021-10-21 23:59:59\",\"contacts\":\"234\"}',12,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-02 10:13:50\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-02 10:13:51','CS888888','2021-10-02 10:13:51'),(1444125366842662914,1,'修改租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"32432\",\"tenantDesc\":\"\",\"accountPrefix\":\"sd\",\"updateTime\":\"2021-10-02 10:01:12\",\"tenantCode\":\"3242\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\",\"createBy\":\"CS888888\",\"tenantName\":\"231e4\",\"createTime\":\"2021-10-02 10:01:12\",\"updateBy\":\"CS888888\",\"tenantId\":1444120246532718600,\"contactNumber\":23432,\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"234\"}',4,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-02 10:21:32\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-02 10:21:33','CS888888','2021-10-02 10:21:33'),(1444126436578926593,1,'修改租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"324\",\"tenantDesc\":\"\",\"accountPrefix\":\"as\",\"updateTime\":\"2021-10-02 09:56:36\",\"tenantCode\":\"23\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\",\"createBy\":\"CS888888\",\"tenantName\":\"23\",\"createTime\":\"2021-10-02 09:56:36\",\"updateBy\":\"CS888888\",\"tenantId\":1444119091375259600,\"contactNumber\":324,\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"234\"}',4,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-02 10:25:47\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-02 10:25:48','CS888888','2021-10-02 10:25:48'),(1444128223805415426,1,'删除租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/delete','POST','{\"tenantId\":1442429609494524000}',10,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-02 10:32:53\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-02 10:32:54','CS888888','2021-10-02 10:32:54'),(1444128236145057793,1,'删除租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/delete','POST','{\"tenantId\":1444120246532718600}',3,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-02 10:32:56\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-02 10:32:57','CS888888','2021-10-02 10:32:57'),(1444128262355263490,1,'删除租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/delete','POST','{\"tenantId\":1444120246532718600}',4,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-02 10:33:03\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-02 10:33:03','CS888888','2021-10-02 10:33:03'),(1444133692741623809,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"21321\",\"tenantName\":\"123\",\"tenantDesc\":\"\",\"contactNumber\":12321,\"accountPrefix\":\"WQ\",\"tenantCode\":\"21321\",\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"21321\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\"}',193,'{\"applicationName\":\"cpms-server-system\",\"code\":20000,\"date\":\"2021-10-02 10:54:37\",\"message\":\"响应成功\",\"success\":true}',0,'CS888888','2021-10-02 10:54:38','CS888888','2021-10-02 10:54:38'),(1444133791483928577,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"21321\",\"tenantName\":\"QWE\",\"tenantDesc\":\"QWEQQWEEEEEEEEEEEEEEEEEEEEEEE\",\"contactNumber\":213213,\"accountPrefix\":\"QW\",\"tenantCode\":\"QWEQW\",\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"213213\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\"}',203,'{\"applicationName\":\"cpms-server-system\",\"code\":20000,\"date\":\"2021-10-02 10:55:01\",\"message\":\"响应成功\",\"success\":true}',0,'CS888888','2021-10-02 10:55:01','CS888888','2021-10-02 10:55:01'),(1444133870605279234,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"21321\",\"tenantName\":\"QWE\",\"tenantDesc\":\"QWEWQ\",\"contactNumber\":123,\"accountPrefix\":\"QW\",\"tenantCode\":\"ASWQWW\",\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"QWE\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\"}',23,'{\"applicationName\":\"cpms-server-system\",\"code\":50008,\"date\":\"2021-10-02 10:55:20\",\"message\":\"账号前缀已存在！！！\",\"success\":false}',0,'CS888888','2021-10-02 10:55:20','CS888888','2021-10-02 10:55:20'),(1444133949697269762,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"21321\",\"tenantName\":\"QWE\",\"tenantDesc\":\"QWEWQ\",\"contactNumber\":123,\"accountPrefix\":\"DS\",\"tenantCode\":\"ASWQWW\",\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"QWE\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\"}',131,'{\"applicationName\":\"cpms-server-system\",\"code\":20000,\"date\":\"2021-10-02 10:55:38\",\"message\":\"响应成功\",\"success\":true}',0,'CS888888','2021-10-02 10:55:39','CS888888','2021-10-02 10:55:39'),(1444134123089797122,1,'新增租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/add','POST','{\"tenantStatus\":\"0\",\"address\":\"232332\",\"tenantName\":\"SDFDS\",\"tenantDesc\":\"\",\"contactNumber\":3233223,\"accountPrefix\":\"HF\",\"tenantCode\":\"BFERTET\",\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"WEW\",\"leaseTimeEnd\":\"2021-11-06 23:59:59\"}',131,'{\"applicationName\":\"cpms-server-system\",\"code\":20000,\"date\":\"2021-10-02 10:56:20\",\"message\":\"响应成功\",\"success\":true}',0,'CS888888','2021-10-02 10:56:20','CS888888','2021-10-02 10:56:20'),(1444199743479193602,1,'修改租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"32432\",\"tenantDesc\":\"\",\"accountPrefix\":\"sd\",\"updateTime\":\"2021-10-02 10:01:12\",\"tenantCode\":\"3242\",\"leaseTimeEnd\":\"2021-10-02 23:59:59\",\"createBy\":\"CS888888\",\"tenantName\":\"231e4\",\"createTime\":\"2021-10-02 10:01:12\",\"updateBy\":\"CS888888\",\"tenantId\":1444120246532718600,\"contactNumber\":23432,\"leaseTimeStart\":\"2021-10-02 23:59:59\",\"contacts\":\"234\"}',15,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-02 15:17:05\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-02 15:17:05','CS888888','2021-10-02 15:17:05'),(1444199809048748033,1,'修改租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳南山区\",\"tenantDesc\":\"Cpms系统软件开发总部，致力服务美容时尚行业，为他们提供各种的互联网软件服务，打造个性化私域流量池\",\"accountPrefix\":\"CS\",\"updateTime\":\"2021-10-02 10:50:38\",\"tenantCode\":\"JU_REN_KE_JI\",\"leaseTimeEnd\":\"2022-12-17 16:41:53\",\"createBy\":\"CS888888\",\"tenantName\":\"深圳巨人科技服务有限公司\",\"createTime\":\"2021-06-13 16:11:43\",\"updateBy\":\"CS888888\",\"tenantId\":1,\"contactNumber\":15889745718,\"leaseTimeStart\":\"2021-09-17 16:41:53\",\"contacts\":\"刘先生\"}',58,'{\"applicationName\":\"cpms-server-system\",\"code\":20008,\"date\":\"2021-10-02 15:17:21\",\"message\":\"操作成功\",\"success\":true}',0,'CS888888','2021-10-02 15:17:21','CS888888','2021-10-02 15:17:21'),(1444201240749252610,1,'修改租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳南山区\",\"tenantDesc\":\"Cpms系统软件开发总部，致力服务美容时尚行业，为他们提供各种的互联网软件服务，打造个性化私域流量池\",\"accountPrefix\":\"CS\",\"updateTime\":\"2021-10-02 15:17:21\",\"tenantCode\":\"JU_REN_KE_JI\",\"leaseTimeEnd\":\"2022-12-17 16:41:53\",\"createBy\":\"CS888888\",\"tenantName\":\"深圳巨人科技服务有限公司\",\"createTime\":\"2021-06-13 16:11:43\",\"updateBy\":\"CS888888\",\"tenantId\":1,\"contactNumber\":15889745718,\"leaseTimeStart\":\"2021-09-17 16:41:53\",\"contacts\":\"刘先生\"}',37,'{\"applicationName\":\"cpms-server-system\",\"code\":20008,\"date\":\"2021-10-02 15:23:02\",\"message\":\"操作成功\",\"success\":true}',0,'CS888888','2021-10-02 15:23:02','CS888888','2021-10-02 15:23:02'),(1444202695073832962,1,'修改租户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳南山区\",\"tenantDesc\":\"Cpms系统软件开发总部，致力服务美容时尚行业，为他们提供各种的互联网软件服务，打造个性化私域流量池\",\"accountPrefix\":\"CS\",\"updateTime\":\"2021-10-02 15:23:02\",\"tenantCode\":\"JU_REN_KE_JI\",\"leaseTimeEnd\":\"2022-12-17 16:41:53\",\"createBy\":\"CS888888\",\"tenantName\":\"深圳巨人科技服务有限公司\",\"createTime\":\"2021-06-13 16:11:43\",\"updateBy\":\"CS888888\",\"tenantId\":1,\"contactNumber\":15889745718,\"leaseTimeStart\":\"2021-09-17 16:41:53\",\"contacts\":\"刘先生\"}',58,'{\"applicationName\":\"cpms-server-system\",\"code\":20008,\"date\":\"2021-10-02 15:28:49\",\"message\":\"操作成功\",\"success\":true}',0,'CS888888','2021-10-02 15:28:49','CS888888','2021-10-02 15:28:49'),(1444467937515249666,1,'删除用户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-user/delete','POST','{\"userId\":1437695137926684674}',234,'{\"applicationName\":\"cpms-server-system\",\"code\":20009,\"date\":\"2021-10-03 09:02:45\",\"message\":\"操作失败！！！\",\"success\":false}',0,'CS888888','2021-10-03 09:02:48','CS888888','2021-10-03 09:02:48'),(1444468769178624002,1,'删除用户','cpms-server-system','127.0.0.1','/systemMicroservice/sys-user/delete','POST','{\"userId\":1437695137926684674}',527,'{\"applicationName\":\"cpms-server-system\",\"code\":20008,\"date\":\"2021-10-03 09:06:04\",\"message\":\"操作成功\",\"success\":true}',0,'CS888888','2021-10-03 09:06:06','CS888888','2021-10-03 09:06:06');

/*Table structure for table `cpms_system_menu` */

DROP TABLE IF EXISTS `cpms_system_menu`;

CREATE TABLE `cpms_system_menu` (
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单|按钮名称',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(128) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `type` tinyint(3) DEFAULT '0' COMMENT '菜单类型:0-菜单，1-按钮',
  `open_flag` tinyint(3) DEFAULT '0' COMMENT '是否新窗口打开:0-不是，1-是',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `cpms_system_menu` */

insert  into `cpms_system_menu`(`menu_id`,`parent_id`,`name`,`code`,`alias`,`path`,`icon`,`component`,`sort`,`type`,`open_flag`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,0,'系统管理','system_manage','menu','/system','el-icon-setting',NULL,1,0,0,0,'2021-08-07 15:42:40','2021-09-30 22:38:03','000001','000001'),(2,1,'用户管理','user_manage','menu','/system/user','el-icon-user-solid',NULL,2,0,0,0,'2021-08-07 15:45:10','2021-10-01 10:54:13','000001','000001'),(3,2,'查看','sys_user_view','view',NULL,NULL,NULL,3,1,0,0,'2021-08-07 15:46:57','2021-08-07 15:53:33','000001','000001'),(4,2,'删除','sys_user_delete','delete',NULL,NULL,NULL,4,1,0,0,'2021-08-07 15:47:41','2021-08-07 15:53:32','000001','000001'),(5,2,'添加','sys_user_add','add',NULL,NULL,NULL,5,1,0,0,'2021-08-07 18:15:39','2021-08-07 18:16:53','000001','000001'),(6,2,'编辑','sys_user_edit','edit',NULL,NULL,NULL,6,1,0,0,'2021-08-07 18:18:10','2021-08-07 18:19:35','000001','000001'),(7,1,'菜单管理','menu_manage','menu','/system/menu','el-icon-menu',NULL,3,0,0,0,'2021-08-08 11:52:10','2021-09-30 21:55:57','000001','000001'),(8,7,'查看','sys_menu_view','view',NULL,NULL,NULL,1,1,0,0,'2021-08-08 11:53:55','2021-10-01 10:15:38','000001','000001'),(9,7,'删除','sys_menu_delete','delete',NULL,NULL,NULL,2,1,0,0,'2021-08-08 11:55:17','2021-08-08 11:57:26','000001','000001'),(10,7,'添加','sys_menu_add','add',NULL,NULL,NULL,3,1,0,0,'2021-08-08 11:56:54','2021-08-08 11:57:48','000001','000001'),(11,7,'编辑','sys_menu_edit','edit',NULL,NULL,NULL,4,1,0,0,'2021-08-08 11:58:31','2021-08-08 11:59:16','000001','000001'),(12,1,'部门管理','dept_manage','menu','/system/dept','el-icon-s-grid','',0,0,0,0,'2021-09-30 22:10:32','2021-09-30 22:42:26','000001','000001'),(1443484962904367106,1,'角色管理','role_manage','menu','/system/role','el-icon-user','',0,0,0,0,'2021-09-30 15:56:48','2021-09-30 22:08:06','000001','000001'),(1443771116924960769,1,'租户管理','tenant_manage','menu','/system/tenant','el-icon-s-custom','',0,0,0,0,'2021-10-01 10:53:53','2021-10-01 10:53:53','000001','000001');

/*Table structure for table `cpms_system_post` */

DROP TABLE IF EXISTS `cpms_system_post`;

CREATE TABLE `cpms_system_post` (
  `post_id` bigint(64) NOT NULL COMMENT '岗位ID',
  `dept_id` bigint(64) NOT NULL COMMENT '部门ID',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '岗位描述',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

/*Data for the table `cpms_system_post` */

insert  into `cpms_system_post`(`post_id`,`dept_id`,`post_name`,`post_desc`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,2,'后端开发工程师','',0,'2021-09-27 10:09:31','2021-09-27 10:09:31','000001','000001'),(2,2,'后端开发高级工程师','',0,'2021-09-27 10:13:09','2021-09-27 10:13:09','000001','000001'),(3,3,'测试','',0,'2021-09-27 15:30:33','2021-09-27 15:30:41','000001','000001');

/*Table structure for table `cpms_system_role` */

DROP TABLE IF EXISTS `cpms_system_role`;

CREATE TABLE `cpms_system_role` (
  `role_id` bigint(64) unsigned NOT NULL COMMENT '角色id',
  `tenant_id` bigint(64) NOT NULL COMMENT '租户ID',
  `dept_id` bigint(64) NOT NULL COMMENT '部门ID',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

/*Data for the table `cpms_system_role` */

insert  into `cpms_system_role`(`role_id`,`tenant_id`,`dept_id`,`role_name`,`role_code`,`role_desc`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,2,'系统超级管理员','SUPER_ADMINISTRATOR',NULL,0,'2021-06-13 16:08:23','2021-09-17 11:15:46','000001','000001'),(2,2,3,'管理员','TENANT_ADMINISTRATOR',NULL,0,'2021-06-13 16:08:23','2021-09-27 16:36:30','000001','000001'),(3,1,2,'推广专员','TUI_GUANG',NULL,0,'2021-08-06 17:34:20','2021-09-17 11:15:48','000001','000001'),(4,1,2,'市场运营','YUN_YING',NULL,0,'2021-09-15 11:33:29','2021-09-17 11:15:51','000001','000001'),(1442414898837250049,1442414898631729154,1442414898812084225,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-09-27 17:04:45','2021-09-27 17:04:45','000001','000001'),(1442429609519689729,1442429609494523905,1442429609511301122,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-09-27 18:03:13','2021-09-27 18:03:13','000001','000001'),(1442702604997832705,1442702604909752321,1442702604972666882,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001'),(1444119091446562817,1444119091375259650,1444119091383648258,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-10-02 09:56:36','2021-10-02 09:56:36','CS888888','CS888888'),(1444120246532718595,1444120246532718593,1444120246532718594,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-10-02 10:01:12','2021-10-02 10:01:12','CS888888','CS888888'),(1444133692150136834,1444133691953004545,1444133691953004546,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-10-02 10:54:38','2021-10-02 10:54:38','CS888888','CS888888'),(1444133790896635906,1444133790506565633,1444133790506565634,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-10-02 10:55:01','2021-10-02 10:55:01','CS888888','CS888888'),(1444133949051256836,1444133949051256834,1444133949051256835,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-10-02 10:55:39','2021-10-02 10:55:39','CS888888','CS888888'),(1444134122494115842,1444134122431201281,1444134122494115841,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-10-02 10:56:20','2021-10-02 10:56:20','CS888888','CS888888');

/*Table structure for table `cpms_system_role_menu` */

DROP TABLE IF EXISTS `cpms_system_role_menu`;

CREATE TABLE `cpms_system_role_menu` (
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '菜单类型:0-侧栏菜单，1-顶部菜单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';

/*Data for the table `cpms_system_role_menu` */

insert  into `cpms_system_role_menu`(`role_id`,`menu_id`,`type`) values (2,7,0),(2,1,0),(2,8,0),(2,9,0),(2,5,0),(2,6,0),(2,4,0),(1,2,1);

/*Table structure for table `cpms_system_role_user` */

DROP TABLE IF EXISTS `cpms_system_role_user`;

CREATE TABLE `cpms_system_role_user` (
  `user_id` bigint(64) NOT NULL COMMENT '用户ID',
  `role_id` bigint(64) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色用户关系表';

/*Data for the table `cpms_system_role_user` */

insert  into `cpms_system_role_user`(`user_id`,`role_id`) values (1,1),(2,2),(2,3),(1438021853006004225,1),(1438021853006004225,4),(1438055567887011842,1),(1438055567887011842,4),(1442429609800708097,1442429609519689729),(1442702605840887809,1442702604997832705),(1444119091840827393,1444119091446562817),(1444120246851485697,1444120246532718595),(1444133692473098242,1444133692150136834),(1444133791227985922,1444133790896635906),(1444133949374218241,1444133949051256836),(1444134122821271553,1444134122494115842);

/*Table structure for table `cpms_system_tenant` */

DROP TABLE IF EXISTS `cpms_system_tenant`;

CREATE TABLE `cpms_system_tenant` (
  `tenant_id` bigint(64) unsigned NOT NULL COMMENT '租户ID，所有的用户都会涉及到这个id',
  `tenant_name` varchar(50) NOT NULL DEFAULT '' COMMENT '租户名称',
  `tenant_code` varchar(50) NOT NULL DEFAULT '' COMMENT '租户编码',
  `contacts` varchar(20) NOT NULL DEFAULT '' COMMENT '联系人',
  `contact_number` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `tenant_desc` varchar(1000) DEFAULT '' COMMENT '租户描述',
  `address` varchar(500) NOT NULL DEFAULT '' COMMENT '地址',
  `lease_time_start` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '租赁开始时间',
  `lease_time_end` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '租赁结束时间',
  `account_prefix` varchar(5) DEFAULT NULL COMMENT '账号前缀限制两个大写字母',
  `tenant_status` tinyint(3) DEFAULT '0' COMMENT '0-正常，1-已禁用',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0:未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户表';

/*Data for the table `cpms_system_tenant` */

insert  into `cpms_system_tenant`(`tenant_id`,`tenant_name`,`tenant_code`,`contacts`,`contact_number`,`tenant_desc`,`address`,`lease_time_start`,`lease_time_end`,`account_prefix`,`tenant_status`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'深圳巨人科技服务有限公司','CPMS_HEADQUARTERS','刘先生','15889745718','Cpms系统软件开发总部，致力服务美容时尚行业，为他们提供各种的互联网软件服务，打造个性化私域流量池','深圳南山区','2021-09-17 16:41:53','2022-12-17 16:41:53','CS',0,0,'2021-06-13 16:11:43','2021-10-03 12:51:34','CS888888','CS888888'),(2,'深圳梦想科技有限公司','','赵先生','15589745664','','深圳','2021-09-17 16:41:53','2022-12-17 16:41:53','MX',0,0,'2021-06-13 16:11:43','2021-10-01 11:28:48','CS888888','CS888888'),(1442414898631729154,'深圳爱美造型时尚有限公司','','王先生','1589545645','','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','AM',0,0,'2021-09-27 17:04:45','2021-10-01 11:28:46','CS888888','CS888888'),(1442429609494523905,'深圳美容资讯有限公司','','王先生','1589545645','','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','ZX',0,0,'2021-09-27 18:03:13','2021-10-01 11:28:46','CS888888','CS888888'),(1442702604909752321,'深圳发型改造有限公司','','王先生','1589545645','','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','FX',0,0,'2021-09-28 12:08:00','2021-10-01 11:28:45','CS888888','CS888888'),(1444119091375259650,'23','23','234','324','','324','2021-10-02 23:59:59','2021-10-02 23:59:59','as',0,0,'2021-10-02 09:56:36','2021-10-02 09:56:36','CS888888','CS888888'),(1444120246532718593,'231e4','3242','234','23432','','32432','2021-10-02 23:59:59','2021-10-02 23:59:59','sd',0,0,'2021-10-02 10:01:12','2021-10-02 10:01:12','CS888888','CS888888'),(1444133691953004545,'123','21321','21321','12321','','21321','2021-10-02 23:59:59','2021-10-02 23:59:59','WQ',0,0,'2021-10-02 10:54:38','2021-10-02 10:54:38','CS888888','CS888888'),(1444133790506565633,'QWE','QWEQW','213213','213213','QWEQQWEEEEEEEEEEEEEEEEEEEEEEE','21321','2021-10-02 23:59:59','2021-10-02 23:59:59','QW',0,0,'2021-10-02 10:55:01','2021-10-02 10:55:01','CS888888','CS888888'),(1444133949051256834,'QWE','ASWQWW','QWE','123','QWEWQ','21321','2021-10-02 23:59:59','2021-10-02 23:59:59','DS',0,0,'2021-10-02 10:55:39','2021-10-02 10:55:39','CS888888','CS888888'),(1444134122431201281,'SDFDS','BFERTET','WEW','3233223','','232332','2021-10-02 23:59:59','2021-11-06 23:59:59','HF',0,0,'2021-10-02 10:56:20','2021-10-02 10:56:20','CS888888','CS888888');

/*Table structure for table `cpms_system_top_menu` */

DROP TABLE IF EXISTS `cpms_system_top_menu`;

CREATE TABLE `cpms_system_top_menu` (
  `top_menu_id` bigint(64) unsigned NOT NULL,
  `tenant_id` bigint(64) NOT NULL COMMENT '租户ID',
  `top_menu_name` varchar(50) NOT NULL DEFAULT '' COMMENT '顶部菜单名称',
  `path` varchar(100) NOT NULL DEFAULT '' COMMENT 'url路径',
  `relation_menu_ids` text COMMENT '关联菜单ID',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-菜单，1-外链',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) NOT NULL COMMENT '创建人工号',
  `update_by` varchar(50) NOT NULL COMMENT '更新人工号',
  PRIMARY KEY (`top_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顶部菜单表';

/*Data for the table `cpms_system_top_menu` */

insert  into `cpms_system_top_menu`(`top_menu_id`,`tenant_id`,`top_menu_name`,`path`,`relation_menu_ids`,`icon`,`type`,`sort`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,'监控系统','','1,7','el-icon-s-tools',0,0,0,'2021-08-30 11:29:38','2021-09-29 11:34:58','000002','000002'),(2,1,'工作台','','1,2,7','el-icon-s-platform',0,1,0,'2021-08-30 11:32:23','2021-09-29 11:35:22','000002','000002');

/*Table structure for table `cpms_system_user` */

DROP TABLE IF EXISTS `cpms_system_user`;

CREATE TABLE `cpms_system_user` (
  `user_id` bigint(64) unsigned NOT NULL,
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `dept_id` bigint(64) NOT NULL COMMENT '所属部门ID',
  `post_id` bigint(64) DEFAULT NULL COMMENT '所属岗位ID',
  `user_account` varchar(50) NOT NULL COMMENT '用户账号',
  `user_password` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户密码',
  `user_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  `user_status` tinyint(3) DEFAULT '0' COMMENT '0-正常，1-已禁用',
  `user_avatar` varchar(2000) DEFAULT NULL COMMENT '用户头像',
  `user_sex` tinyint(3) DEFAULT '0' COMMENT '用户性别：0-未知，1-男，2-女',
  `user_mobile` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `user_login_ip` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '登录IP地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`user_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理用户表';

/*Data for the table `cpms_system_user` */

insert  into `cpms_system_user`(`user_id`,`tenant_id`,`dept_id`,`post_id`,`user_account`,`user_password`,`user_name`,`user_status`,`user_avatar`,`user_sex`,`user_mobile`,`del_flag`,`user_login_ip`,`last_login_time`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,2,2,'CS888888','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','superAdmin',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,'127.0.0.1','2020-05-17 14:27:19','2021-06-13 16:15:34','2021-10-01 11:05:10','000001','000001'),(2,2,3,0,'000002','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','Mr.Liu',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',0,'15889745719',0,'127.0.0.1','2020-05-15 14:27:50','2021-06-13 16:15:34','2021-09-14 16:57:17','000001','000001'),(1437695137926684674,1,2,0,'test001','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','test001',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-15 19:35:07','2021-09-14 16:30:07','2021-10-03 09:06:37','000001','000001'),(1437696509359235073,1,2,2,'test002','$2a$10$6DOwEXYOWCTQzyrezO2BlOQqbEN648S66qhh0xZPuSB1eLg4tLrY2','6666',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-15 19:35:04','2021-09-14 16:35:34','2021-09-27 10:54:20','000001','000001'),(1437698974959869953,1,2,1,'test003','$2a$10$AYAhHljph7h8om9EPP.X8.gUgRPNkoi55MwTh7zS/6hVBHkaPoNs.','test001',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-21 19:35:42','2021-09-14 16:45:21','2021-09-27 10:14:16','000001','000001'),(1438021853006004225,1,2,1,'test00225','$2a$10$pNh5tZW7B5Ljx1Y5bXu4LugI5.6dK9HwoObw4YSi.NlAnX6vGbDrq','test001',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-15 19:35:09','2021-09-15 14:08:22','2021-09-27 10:14:18','000001','000001'),(1438055567887011842,1,2,1,'TTTT','$2a$10$9JtVQQ7NZfDfY12s.7mvIewWptl2OIwghmHrGxLDZdlQrekKE52Gu','999',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-24 19:35:37','2021-09-15 16:22:20','2021-09-27 10:14:23','000001','000001'),(1442429609800708097,1442429609494523905,1442429609511301122,NULL,'admin','$2a$10$OCTcvriWiqhIXUFEzVuRe.JUYWR30Bdu8BnXuLxQUMul3Y9nIci86','王先生',0,NULL,0,'1589545645',0,NULL,NULL,'2021-09-27 18:03:13','2021-09-27 18:03:13','000001','000001'),(1442702605840887809,1442702604909752321,1442702604972666882,NULL,'FX000001','$2a$10$48vhg0DKnMc.Jfs0jAT2ouHgab3Ce0/O7QTRSK8BAB3KiFbIY.mla','王先生',0,NULL,0,'1589545645',0,NULL,NULL,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001'),(1444119091840827393,1444119091375259650,1444119091383648258,NULL,'as000001','$2a$10$2Arz8WozaoA.O4FOj/2DA.922ojPCS8egvlLDcFLC1AoZEcv2lRri','234',0,NULL,0,'324',0,NULL,NULL,'2021-10-02 09:56:37','2021-10-02 09:56:37','CS888888','CS888888'),(1444120246851485697,1444120246532718593,1444120246532718594,NULL,'sd000001','$2a$10$kJcIHK9GXgzNImLFOSEaOOuoFCkoz0mLs2pdT7wuq2g8Tu7KPTWlK','234',0,NULL,0,'23432',0,NULL,NULL,'2021-10-02 10:01:12','2021-10-02 10:01:12','CS888888','CS888888'),(1444133692473098242,1444133691953004545,1444133691953004546,NULL,'WQ000001','$2a$10$cw3ifQYK/RjIHDkOmiqoJ.DY.CavNJZfcfSpAD1.IYmCOsaP7iMii','21321',0,NULL,0,'12321',0,NULL,NULL,'2021-10-02 10:54:38','2021-10-02 10:54:38','CS888888','CS888888'),(1444133791227985922,1444133790506565633,1444133790506565634,NULL,'QW000001','$2a$10$XafGiD7C1UHukaQ3jmqJ4u9eVvCYRtscykEQ..QYpamr0qMJZhE/i','213213',0,NULL,0,'213213',0,NULL,NULL,'2021-10-02 10:55:01','2021-10-02 10:55:01','CS888888','CS888888'),(1444133949374218241,1444133949051256834,1444133949051256835,NULL,'DS000001','$2a$10$UeDyZ2vP5wTvTD0s1JZniezXopUSGrM0nMob6i5PfC7w9bQZmUD6.','QWE',0,NULL,0,'123',0,NULL,NULL,'2021-10-02 10:55:39','2021-10-02 10:55:39','CS888888','CS888888'),(1444134122821271553,1444134122431201281,1444134122494115841,NULL,'HF000001','$2a$10$U3K2m2ZBvs4M.qdpWBDPe.U.3.TjqUU1UzyaM4bqeh9ryamyVLkWG','WEW',0,NULL,0,'3233223',0,NULL,NULL,'2021-10-02 10:56:20','2021-10-02 10:56:20','CS888888','CS888888');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
