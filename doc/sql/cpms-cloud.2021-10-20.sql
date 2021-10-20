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
  `dept_sort` int(11) DEFAULT '0' COMMENT '部门排序',
  `parent_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '树形结构，父节点id',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `cpms_system_dept` */

insert  into `cpms_system_dept`(`dept_id`,`tenant_id`,`dept_name`,`dept_desc`,`dept_sort`,`parent_id`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,'深圳巨人科技软件服务有限公司总部','安全稳定人 前端开发小组前端开发小组前端开发小组前端开发小组前端开发小组前端开发小组333333',1,0,0,'2021-06-13 16:13:50','2021-10-20 14:12:52','CS888888','CS888888'),(2,1,'Cpms系统深圳研发部','前端开发小组',0,1,0,'2021-06-13 16:13:50','2021-10-12 19:27:44','CS888888','CS888888'),(3,2,'深圳梦想科技有限公司总部','',0,0,0,'2021-06-13 16:13:50','2021-10-10 19:20:19','CS888888','CS888888'),(4,1,'前端研发组','前端开发小组',0,2,0,'2021-10-10 19:19:06','2021-10-12 19:25:27','CS888888','CS888888'),(5,1,'后端研发组','后端开发小组',0,2,0,'2021-10-10 19:19:34','2021-10-12 19:25:39','CS888888','CS888888'),(1442414898812084225,1442414898631729154,'深圳爱美造型时尚有限公司总部','',0,0,0,'2021-09-27 17:04:45','2021-10-10 19:20:27','CS888888','CS888888'),(1442429609511301122,1442429609494523905,'深圳美容资讯有限公司总部','',0,0,0,'2021-09-27 18:03:13','2021-10-10 19:20:28','CS888888','CS888888'),(1442702604972666882,1442702604909752321,'深圳发型改造有限公司总部','',0,0,0,'2021-09-28 12:08:00','2021-10-10 19:20:33','CS888888','CS888888'),(1444119091383648258,1444119091375259650,'深圳美容美发加盟店总部','',0,0,0,'2021-10-02 09:56:36','2021-10-20 11:44:41','CS888888','CS888888'),(1444120246532718594,1444120246532718593,'广州造型设计工作室总部','',0,0,0,'2021-10-02 10:01:12','2021-10-20 11:44:54','CS888888','CS888888'),(1444133691953004546,1444133691953004545,'长沙网红培训基地总部','',0,0,0,'2021-10-02 10:54:38','2021-10-20 11:45:08','CS888888','CS888888'),(1444133790506565634,1444133790506565633,'深圳完美造型设计工作室总部','',0,0,0,'2021-10-02 10:55:01','2021-10-20 11:45:24','CS888888','CS888888'),(1444133949051256835,1444133949051256834,'长沙影视拍摄网红基地总部','',0,0,0,'2021-10-02 10:55:39','2021-10-20 11:45:39','CS888888','CS888888'),(1444134122494115841,1444134122431201281,'广州爱美客服务有限公司总部','',0,0,0,'2021-10-02 10:56:20','2021-10-20 11:46:15','CS888888','CS888888'),(1448203399383867394,1,'Cpms系统运营推广部1','w',0,5,1,'2021-10-13 16:26:11','2021-10-16 10:59:21','CS888888','CS888888'),(1448205663938928641,1,'APP开发小组3','APP应用开发组',0,4,0,'2021-10-13 16:35:11','2021-10-16 16:44:03','CS888888','CS888888'),(1448232581744680962,1,'功能测试组','',20,2,0,'2021-10-13 18:22:09','2021-10-20 14:24:15','CS888888','CS888888'),(1449296325081935874,1449296325081935873,'深圳发型改造咨询工作室总部','',0,0,0,'2021-10-16 16:49:05','2021-10-20 11:46:35','CS888888','CS888888');

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

insert  into `cpms_system_menu`(`menu_id`,`parent_id`,`name`,`code`,`alias`,`path`,`icon`,`component`,`sort`,`type`,`open_flag`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,0,'系统管理','system_manage','menu','/system','el-icon-setting',NULL,1,0,0,0,'2021-08-07 15:42:40','2021-09-30 22:38:03','000001','000001'),(2,1,'用户管理','user_manage','menu','/system/user','el-icon-user-solid',NULL,2,0,0,0,'2021-08-07 15:45:10','2021-10-01 10:54:13','000001','000001'),(3,2,'查看','sys_user_view','view',NULL,NULL,NULL,3,1,0,0,'2021-08-07 15:46:57','2021-08-07 15:53:33','000001','000001'),(4,2,'删除','sys_user_delete','delete',NULL,NULL,NULL,4,1,0,0,'2021-08-07 15:47:41','2021-08-07 15:53:32','000001','000001'),(5,2,'添加','sys_user_add','add',NULL,NULL,NULL,5,1,0,0,'2021-08-07 18:15:39','2021-08-07 18:16:53','000001','000001'),(6,2,'编辑','sys_user_edit','edit',NULL,NULL,NULL,6,1,0,0,'2021-08-07 18:18:10','2021-08-07 18:19:35','000001','000001'),(7,1,'菜单管理','menu_manage','menu','/system/menu','el-icon-menu',NULL,3,0,0,0,'2021-08-08 11:52:10','2021-09-30 21:55:57','000001','000001'),(8,7,'查看','sys_menu_view','view',NULL,NULL,NULL,1,1,0,0,'2021-08-08 11:53:55','2021-10-01 10:15:38','000001','000001'),(9,7,'删除','sys_menu_delete','delete',NULL,NULL,NULL,2,1,0,0,'2021-08-08 11:55:17','2021-08-08 11:57:26','000001','000001'),(10,7,'添加','sys_menu_add','add',NULL,NULL,NULL,3,1,0,0,'2021-08-08 11:56:54','2021-08-08 11:57:48','000001','000001'),(11,7,'编辑','sys_menu_edit','edit',NULL,NULL,NULL,4,1,0,0,'2021-08-08 11:58:31','2021-08-08 11:59:16','000001','000001'),(12,1,'部门管理','dept_manage','menu','/system/dept','el-icon-s-grid','',5,0,0,0,'2021-09-30 22:10:32','2021-10-16 16:43:32','000001','CS888888'),(1443484962904367106,1,'角色管理','role_manage','menu','/system/role','el-icon-user','',0,0,0,0,'2021-09-30 15:56:48','2021-09-30 22:08:06','000001','000001'),(1443771116924960769,1,'租户管理','tenant_manage','menu','/system/tenant','el-icon-s-custom','',4,0,0,0,'2021-10-01 10:53:53','2021-10-16 16:42:59','000001','CS888888'),(1449236520891928577,1,'岗位管理','post_manage','menu','/system/post','el-icon-postcard','',0,0,0,0,'2021-10-16 12:51:27','2021-10-16 12:51:27','CS888888','CS888888');

/*Table structure for table `cpms_system_post` */

DROP TABLE IF EXISTS `cpms_system_post`;

CREATE TABLE `cpms_system_post` (
  `post_id` bigint(64) NOT NULL COMMENT '岗位ID',
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_code` varchar(30) NOT NULL DEFAULT '' COMMENT '岗位编码',
  `post_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '岗位描述',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

/*Data for the table `cpms_system_post` */

insert  into `cpms_system_post`(`post_id`,`tenant_id`,`post_name`,`post_code`,`post_desc`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,'后端开发工程师','CEO','后端开发工程师',0,'2021-09-27 10:09:31','2021-10-16 15:32:04','000001','000001'),(2,1,'后端开发高级工程师','CFO','后端开发高级工程师后端开发高级工程师后端开发高级工程师后端开发高级工程师后端开发高级工程师',0,'2021-09-27 10:13:09','2021-10-16 15:32:10','000001','000001'),(3,2,'测试','CS','',0,'2021-09-27 15:30:33','2021-10-16 15:32:17','000001','000001'),(1449281567540641793,1,'JJJJ','COO','23',1,'2021-10-16 15:50:27','2021-10-16 16:40:35','CS888888','CS888888'),(1449281591368482817,1,'高级运维工程师','YW','项目部署，监控',0,'2021-10-16 15:50:32','2021-10-17 11:17:34','CS888888','CS888888'),(1449281756766666754,1,'大数据开发工程师','DF','23SDDSSDDSDDDDDDDDDD',1,'2021-10-16 15:51:12','2021-10-16 16:41:50','CS888888','CS888888'),(1449281778509938689,1,'23432','wer','23',0,'2021-10-16 15:51:17','2021-10-16 15:51:17','CS888888','CS888888'),(1449281805370261506,1,'234','234','324',0,'2021-10-16 15:51:23','2021-10-16 15:51:23','CS888888','CS888888'),(1449281830699663361,1,'234','234','234',0,'2021-10-16 15:51:29','2021-10-16 15:51:29','CS888888','CS888888'),(1449281859325788162,1,'234','345','324',0,'2021-10-16 15:51:36','2021-10-16 15:51:36','CS888888','CS888888'),(1449281883501756417,1,'儿童1111','43522222','345777777',0,'2021-10-16 15:51:42','2021-10-17 11:12:40','CS888888','CS888888'),(1449281914338279425,1,'GGGG','e让她','儿童',0,'2021-10-16 15:51:49','2021-10-16 16:07:07','CS888888','CS888888'),(1449281934781317121,1,'HHHHH','345','345',0,'2021-10-16 15:51:54','2021-10-16 16:08:55','CS888888','CS888888'),(1449282005933490178,1,'23432','32423','324',0,'2021-10-16 15:52:11','2021-10-16 15:52:11','CS888888','CS888888'),(1449283015091421185,1,'财务审核专员','COO','财务管理',0,'2021-10-16 15:56:12','2021-10-17 11:17:10','CS888888','CS888888'),(1449283205265358850,1,'23142','CTOfffff','we热舞',0,'2021-10-16 15:56:57','2021-10-16 16:05:48','CS888888','CS888888'),(1450767760723918849,1,'总经理','GM','公司总经理',0,'2021-10-20 18:16:03','2021-10-20 18:16:03','CS888888','CS888888');

/*Table structure for table `cpms_system_role` */

DROP TABLE IF EXISTS `cpms_system_role`;

CREATE TABLE `cpms_system_role` (
  `role_id` bigint(64) unsigned NOT NULL COMMENT '角色id',
  `tenant_id` bigint(64) NOT NULL COMMENT '租户ID',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `role_sort` int(11) DEFAULT '1' COMMENT '角色排序',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

/*Data for the table `cpms_system_role` */

insert  into `cpms_system_role`(`role_id`,`tenant_id`,`role_name`,`role_code`,`role_desc`,`role_sort`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,'系统超级管理员','SUPER_ADMINISTRATOR','系统超级管理员系统超级管理员系统超级管理员系统超级管理员',1,0,'2021-06-13 16:08:23','2021-10-17 12:22:06','000001','000001'),(2,2,'管理员','TENANT_ADMINISTRATOR','管理员管理员管理员',1,0,'2021-06-13 16:08:23','2021-10-17 12:21:41','000001','000001'),(3,1,'推广专员','TUI_GUANG','推广专员推广专员22222',2,0,'2021-08-06 17:34:20','2021-10-17 12:49:44','000001','CS888888'),(4,1,'市场运营','YUN_YING','市场运营市场运营',1,0,'2021-09-15 11:33:29','2021-10-17 12:21:50','000001','000001'),(1442414898837250049,1442414898631729154,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-09-27 17:04:45','2021-09-27 17:04:45','000001','000001'),(1442429609519689729,1442429609494523905,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-09-27 18:03:13','2021-09-27 18:03:13','000001','000001'),(1442702604997832705,1442702604909752321,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001'),(1444119091446562817,1444119091375259650,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-10-02 09:56:36','2021-10-02 09:56:36','CS888888','CS888888'),(1444120246532718595,1444120246532718593,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-10-02 10:01:12','2021-10-02 10:01:12','CS888888','CS888888'),(1444133692150136834,1444133691953004545,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-10-02 10:54:38','2021-10-02 10:54:38','CS888888','CS888888'),(1444133790896635906,1444133790506565633,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-10-02 10:55:01','2021-10-02 10:55:01','CS888888','CS888888'),(1444133949051256836,1444133949051256834,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-10-02 10:55:39','2021-10-02 10:55:39','CS888888','CS888888'),(1444134122494115842,1444134122431201281,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-10-02 10:56:20','2021-10-02 10:56:20','CS888888','CS888888'),(1449296325081935875,1449296325081935873,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',1,0,'2021-10-16 16:49:05','2021-10-16 16:49:05','CS888888','CS888888'),(1449598900826030082,1,'文案策划','WEN_AN','策划广告文案',1,0,'2021-10-17 12:51:25','2021-10-17 12:51:25','CS888888','CS888888'),(1449600821116813314,1,'1221','2121','2121',1,1,'2021-10-17 12:59:03','2021-10-17 12:59:08','CS888888','CS888888');

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
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色用户关系表';

/*Data for the table `cpms_system_role_user` */

insert  into `cpms_system_role_user`(`user_id`,`role_id`) values (1,1),(2,2),(2,3),(1438021853006004225,1),(1438021853006004225,4),(1438055567887011842,1),(1438055567887011842,4),(1442429609800708097,1442429609519689729),(1442702605840887809,1442702604997832705),(1444119091840827393,1444119091446562817),(1444120246851485697,1444120246532718595),(1444133692473098242,1444133692150136834),(1444133791227985922,1444133790896635906),(1444133949374218241,1444133949051256836),(1444134122821271553,1444134122494115842),(1449296325543309314,1449296325081935875);

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

insert  into `cpms_system_tenant`(`tenant_id`,`tenant_name`,`tenant_code`,`contacts`,`contact_number`,`tenant_desc`,`address`,`lease_time_start`,`lease_time_end`,`account_prefix`,`tenant_status`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'深圳巨人科技服务有限公司','CPMS_HEADQUARTERS','刘先生','15889745718','Cpms系统软件开发总部，致力服务美容时尚行业，为他们提供各种的互联网软件服务，打造个性化私域流量池','深圳南山区','2021-09-17 16:41:53','2022-12-17 16:41:53','CS',0,0,'2021-06-13 16:11:43','2021-10-03 12:51:34','CS888888','CS888888'),(2,'深圳梦想科技有限公司','','赵先生','15589745664','','深圳','2021-09-17 16:41:53','2022-12-17 16:41:53','MX',0,0,'2021-06-13 16:11:43','2021-10-01 11:28:48','CS888888','CS888888'),(1442414898631729154,'深圳爱美造型时尚有限公司','','王先生','1589545645','','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','AM',0,0,'2021-09-27 17:04:45','2021-10-01 11:28:46','CS888888','CS888888'),(1442429609494523905,'深圳美容资讯有限公司','','王先生','1589545645','','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','ZX',0,0,'2021-09-27 18:03:13','2021-10-01 11:28:46','CS888888','CS888888'),(1442702604909752321,'深圳发型改造有限公司','','王先生','1589545645','','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','FX',0,0,'2021-09-28 12:08:00','2021-10-01 11:28:45','CS888888','CS888888'),(1444119091375259650,'深圳美容美发加盟店','JMD','徐女士','1223544887','SDFDSFDS','666666666666666666666666666666','2021-10-30 23:59:59','2022-10-31 23:59:59','as',0,0,'2021-10-02 09:56:36','2021-10-17 11:10:17','CS888888','CS888888'),(1444120246532718593,'广州造型设计工作室','3242','234','23432','','32432','2021-10-02 23:59:59','2021-11-11 23:59:59','sd',0,0,'2021-10-02 10:01:12','2021-10-17 11:07:24','CS888888','CS888888'),(1444133691953004545,'长沙网红培训基地','21321','21321','12321','','21321','2021-10-02 23:59:59','2021-10-02 23:59:59','WQ',0,0,'2021-10-02 10:54:38','2021-10-20 11:42:30','CS888888','CS888888'),(1444133790506565633,'深圳完美造型设计工作室','QWEQW','213213','213213','QWEQQWEEEEEEEEEEEEEEEEEEEEEEE','21321','2021-10-02 23:59:59','2021-10-02 23:59:59','QW',0,0,'2021-10-02 10:55:01','2021-10-20 11:43:04','CS888888','CS888888'),(1444133949051256834,'长沙影视拍摄网红基地','ASWQWW','QWE','123','QWEWQ','21321','2021-10-02 23:59:59','2021-10-02 23:59:59','DS',0,0,'2021-10-02 10:55:39','2021-10-20 11:43:38','CS888888','CS888888'),(1444134122431201281,'广州爱美客服务有限公司','BFERTET','WEW','3233223','','232332','2021-10-02 23:59:59','2021-12-30 23:59:59','HF',0,0,'2021-10-02 10:56:20','2021-10-16 16:56:19','CS888888','CS888888'),(1449296325081935873,'深圳发型改造咨询工作室','666666','2347777777','324234324545445','','广州222','2021-10-22 23:59:59','2021-12-30 23:59:59','sf',0,0,'2021-10-16 16:49:05','2021-10-20 10:28:43','CS888888','CS888888');

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
  `user_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户昵称',
  `user_real_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户真实姓名',
  `user_status` tinyint(3) DEFAULT '0' COMMENT '0-正常，1-已禁用',
  `user_avatar` varchar(2000) DEFAULT NULL COMMENT '用户头像',
  `user_sex` tinyint(3) DEFAULT '0' COMMENT '用户性别：0-未知，1-男，2-女',
  `user_birthday` datetime DEFAULT NULL COMMENT '用户生日日期',
  `user_mobile` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `user_login_ip` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '登录IP地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account_idex` (`user_account`),
  KEY `tenant_dept_post_idx` (`tenant_id`,`dept_id`,`post_id`),
  KEY `user_name_idex` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理用户表';

/*Data for the table `cpms_system_user` */

insert  into `cpms_system_user`(`user_id`,`tenant_id`,`dept_id`,`post_id`,`user_account`,`user_password`,`user_name`,`user_real_name`,`user_status`,`user_avatar`,`user_sex`,`user_birthday`,`user_mobile`,`del_flag`,`user_login_ip`,`last_login_time`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,1,1450767760723918849,'CS888888','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','superAdmin','刘先生',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,NULL,'15889745718',0,'127.0.0.1','2020-05-17 14:27:19','2021-06-13 16:15:34','2021-10-20 18:17:15','000001','000001'),(2,2,3,0,'000002','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','Mr.Liu','',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',0,NULL,'15889745719',0,'127.0.0.1','2020-05-15 14:27:50','2021-06-13 16:15:34','2021-09-14 16:57:17','000001','000001'),(1437695137926684674,1,4,0,'test001','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','test001','',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,NULL,'15889745718',0,NULL,'2021-09-15 19:35:07','2021-09-14 16:30:07','2021-10-20 16:17:09','000001','000001'),(1437696509359235073,1,4,2,'test002','$2a$10$6DOwEXYOWCTQzyrezO2BlOQqbEN648S66qhh0xZPuSB1eLg4tLrY2','6666','',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,NULL,'15889745718',0,NULL,'2021-09-15 19:35:04','2021-09-14 16:35:34','2021-10-20 16:17:12','000001','000001'),(1437698974959869953,1,5,1,'test003','$2a$10$AYAhHljph7h8om9EPP.X8.gUgRPNkoi55MwTh7zS/6hVBHkaPoNs.','test001','',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,NULL,'15889745718',0,NULL,'2021-09-21 19:35:42','2021-09-14 16:45:21','2021-10-20 16:17:14','000001','000001'),(1438021853006004225,1,2,1,'test00225','$2a$10$pNh5tZW7B5Ljx1Y5bXu4LugI5.6dK9HwoObw4YSi.NlAnX6vGbDrq','test001','',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,NULL,'15889745718',0,NULL,'2021-09-15 19:35:09','2021-09-15 14:08:22','2021-09-27 10:14:18','000001','000001'),(1438055567887011842,1,5,1,'TTTT','$2a$10$9JtVQQ7NZfDfY12s.7mvIewWptl2OIwghmHrGxLDZdlQrekKE52Gu','999','',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,NULL,'15889745718',0,NULL,'2021-09-24 19:35:37','2021-09-15 16:22:20','2021-10-20 16:17:18','000001','000001'),(1442429609800708097,1442429609494523905,1442429609511301122,NULL,'admin','$2a$10$OCTcvriWiqhIXUFEzVuRe.JUYWR30Bdu8BnXuLxQUMul3Y9nIci86','王先生','',0,NULL,0,NULL,'1589545645',0,NULL,NULL,'2021-09-27 18:03:13','2021-09-27 18:03:13','000001','000001'),(1442702605840887809,1442702604909752321,1442702604972666882,NULL,'FX000001','$2a$10$48vhg0DKnMc.Jfs0jAT2ouHgab3Ce0/O7QTRSK8BAB3KiFbIY.mla','王先生','',0,NULL,0,NULL,'1589545645',0,NULL,NULL,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001'),(1444119091840827393,1444119091375259650,1444119091383648258,NULL,'as000001','$2a$10$2Arz8WozaoA.O4FOj/2DA.922ojPCS8egvlLDcFLC1AoZEcv2lRri','234','',0,NULL,0,NULL,'324',0,NULL,NULL,'2021-10-02 09:56:37','2021-10-02 09:56:37','CS888888','CS888888'),(1444120246851485697,1444120246532718593,1444120246532718594,NULL,'sd000001','$2a$10$kJcIHK9GXgzNImLFOSEaOOuoFCkoz0mLs2pdT7wuq2g8Tu7KPTWlK','234','',0,NULL,0,NULL,'23432',0,NULL,NULL,'2021-10-02 10:01:12','2021-10-02 10:01:12','CS888888','CS888888'),(1444133692473098242,1444133691953004545,1444133691953004546,NULL,'WQ000001','$2a$10$cw3ifQYK/RjIHDkOmiqoJ.DY.CavNJZfcfSpAD1.IYmCOsaP7iMii','21321','',0,NULL,0,NULL,'12321',0,NULL,NULL,'2021-10-02 10:54:38','2021-10-02 10:54:38','CS888888','CS888888'),(1444133791227985922,1444133790506565633,1444133790506565634,NULL,'QW000001','$2a$10$XafGiD7C1UHukaQ3jmqJ4u9eVvCYRtscykEQ..QYpamr0qMJZhE/i','213213','',0,NULL,0,NULL,'213213',0,NULL,NULL,'2021-10-02 10:55:01','2021-10-02 10:55:01','CS888888','CS888888'),(1444133949374218241,1444133949051256834,1444133949051256835,NULL,'DS000001','$2a$10$UeDyZ2vP5wTvTD0s1JZniezXopUSGrM0nMob6i5PfC7w9bQZmUD6.','QWE','',0,NULL,0,NULL,'123',0,NULL,NULL,'2021-10-02 10:55:39','2021-10-02 10:55:39','CS888888','CS888888'),(1444134122821271553,1444134122431201281,1444134122494115841,NULL,'HF000001','$2a$10$U3K2m2ZBvs4M.qdpWBDPe.U.3.TjqUU1UzyaM4bqeh9ryamyVLkWG','WEW','',0,NULL,0,NULL,'3233223',0,NULL,NULL,'2021-10-02 10:56:20','2021-10-02 10:56:20','CS888888','CS888888'),(1449296325543309314,1449296325081935873,1449296325081935874,NULL,'sf000001','$2a$10$SVPjlv8LZrkDrGHQV6Tv/OmHady4Tb9IDkZCnbY/CoavbzHQA0ACG','234','',0,NULL,0,NULL,'32423432',0,NULL,NULL,'2021-10-16 16:49:05','2021-10-16 16:49:05','CS888888','CS888888');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
