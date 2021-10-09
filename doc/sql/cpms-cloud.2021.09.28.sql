/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.26 : Database - cpms-cloud
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `cpms_system_dept` */

DROP TABLE IF EXISTS `cpms_system_dept`;

CREATE TABLE `cpms_system_dept` (
  `dept_id` bigint(64) unsigned NOT NULL COMMENT '部门ID',
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `dept_name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '树形结构，父节点id',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `cpms_system_dept` */

insert  into `cpms_system_dept`(`dept_id`,`tenant_id`,`dept_name`,`parent_id`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,1,'深圳巨人科技软件服务有限公司',0,0,'2021-06-13 16:13:50','2021-08-08 14:10:22','1','1'),
(2,1,'Cpms系统研发部',1,0,'2021-06-13 16:13:50','2021-08-06 16:03:28','1','1'),
(3,2,'深圳梦想科技有限公司',0,0,'2021-06-13 16:13:50','2021-06-13 16:15:56','1','1'),
(1442414898812084225,1442414898631729154,'深圳爱美造型时尚有限公司',0,0,'2021-09-27 17:04:45','2021-09-27 17:04:45','000001','000001'),
(1442429609511301122,1442429609494523905,'深圳美容资讯有限公司',0,0,'2021-09-27 18:03:13','2021-09-27 18:03:13','000001','000001'),
(1442702604972666882,1442702604909752321,'深圳发型改造有限公司',0,0,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001');

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

insert  into `cpms_system_menu`(`menu_id`,`parent_id`,`name`,`code`,`alias`,`path`,`icon`,`component`,`sort`,`type`,`open_flag`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,0,'系统管理','system_manage','menu','/system',NULL,NULL,1,0,0,0,'2021-08-07 15:42:40','2021-08-30 18:04:16','000001','000001'),
(2,1,'用户管理','user_manage','menu','/system/user',NULL,NULL,2,0,0,0,'2021-08-07 15:45:10','2021-08-30 18:04:27','000001','000001'),
(3,2,'查看','sys_user_view','view',NULL,NULL,NULL,3,1,0,0,'2021-08-07 15:46:57','2021-08-07 15:53:33','000001','000001'),
(4,2,'删除','sys_user_delete','delete',NULL,NULL,NULL,4,1,0,0,'2021-08-07 15:47:41','2021-08-07 15:53:32','000001','000001'),
(5,2,'添加','sys_user_add','add',NULL,NULL,NULL,5,1,0,0,'2021-08-07 18:15:39','2021-08-07 18:16:53','000001','000001'),
(6,2,'编辑','sys_user_edit','edit',NULL,NULL,NULL,6,1,0,0,'2021-08-07 18:18:10','2021-08-07 18:19:35','000001','000001'),
(7,1,'菜单管理','menu_manage','menu','/system/menu',NULL,NULL,3,0,0,0,'2021-08-08 11:52:10','2021-08-30 18:04:35','000001','000001'),
(8,7,'查看','sys_menu_view','view',NULL,NULL,NULL,1,1,0,0,'2021-08-08 11:53:55','2021-08-08 11:54:56','000001','000001'),
(9,7,'删除','sys_menu_delete','delete',NULL,NULL,NULL,2,1,0,0,'2021-08-08 11:55:17','2021-08-08 11:57:26','000001','000001'),
(10,7,'添加','sys_menu_add','add',NULL,NULL,NULL,3,1,0,0,'2021-08-08 11:56:54','2021-08-08 11:57:48','000001','000001'),
(11,7,'编辑','sys_menu_edit','edit',NULL,NULL,NULL,4,1,0,0,'2021-08-08 11:58:31','2021-08-08 11:59:16','000001','000001');

/*Table structure for table `cpms_system_post` */

DROP TABLE IF EXISTS `cpms_system_post`;

CREATE TABLE `cpms_system_post` (
  `post_id` bigint(64) NOT NULL COMMENT '岗位ID',
  `dept_id` bigint(64) NOT NULL COMMENT '部门ID',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

/*Data for the table `cpms_system_post` */

insert  into `cpms_system_post`(`post_id`,`dept_id`,`post_name`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,2,'后端开发工程师',0,'2021-09-27 10:09:31','2021-09-27 10:09:31','000001','000001'),
(2,2,'后端开发高级工程师',0,'2021-09-27 10:13:09','2021-09-27 10:13:09','000001','000001'),
(3,3,'测试',0,'2021-09-27 15:30:33','2021-09-27 15:30:41','000001','000001');

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

insert  into `cpms_system_role`(`role_id`,`tenant_id`,`dept_id`,`role_name`,`role_code`,`role_desc`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,1,2,'系统超级管理员','SUPER_ADMINISTRATOR',NULL,0,'2021-06-13 16:08:23','2021-09-17 11:15:46','000001','000001'),
(2,2,3,'管理员','TENANT_ADMINISTRATOR',NULL,0,'2021-06-13 16:08:23','2021-09-27 16:36:30','000001','000001'),
(3,1,2,'推广专员','TUI_GUANG',NULL,0,'2021-08-06 17:34:20','2021-09-17 11:15:48','000001','000001'),
(4,1,2,'市场运营','YUN_YING',NULL,0,'2021-09-15 11:33:29','2021-09-17 11:15:51','000001','000001'),
(1442414898837250049,1442414898631729154,1442414898812084225,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-09-27 17:04:45','2021-09-27 17:04:45','000001','000001'),
(1442429609519689729,1442429609494523905,1442429609511301122,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-09-27 18:03:13','2021-09-27 18:03:13','000001','000001'),
(1442702604997832705,1442702604909752321,1442702604972666882,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',0,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001');

/*Table structure for table `cpms_system_role_menu` */

DROP TABLE IF EXISTS `cpms_system_role_menu`;

CREATE TABLE `cpms_system_role_menu` (
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '菜单类型:0-侧栏菜单，1-顶部菜单'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';

/*Data for the table `cpms_system_role_menu` */

insert  into `cpms_system_role_menu`(`role_id`,`menu_id`,`type`) values 
(2,7,0),
(2,1,0),
(2,8,0),
(2,9,0),
(2,5,0),
(2,6,0),
(2,4,0),
(2,2,1);

/*Table structure for table `cpms_system_role_user` */

DROP TABLE IF EXISTS `cpms_system_role_user`;

CREATE TABLE `cpms_system_role_user` (
  `user_id` bigint(64) NOT NULL COMMENT '用户ID',
  `role_id` bigint(64) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色用户关系表';

/*Data for the table `cpms_system_role_user` */

insert  into `cpms_system_role_user`(`user_id`,`role_id`) values 
(1,1),
(2,2),
(2,3),
(1438021853006004225,1),
(1438021853006004225,4),
(1438055567887011842,1),
(1438055567887011842,4),
(1442429609800708097,1442429609519689729),
(1442702605840887809,1442702604997832705);

/*Table structure for table `cpms_system_tenant` */

DROP TABLE IF EXISTS `cpms_system_tenant`;

CREATE TABLE `cpms_system_tenant` (
  `tenant_id` bigint(64) unsigned NOT NULL COMMENT '租户ID，所有的用户都会涉及到这个id',
  `tenant_name` varchar(50) NOT NULL DEFAULT '' COMMENT '租户名称',
  `contacts` varchar(20) NOT NULL DEFAULT '' COMMENT '联系人',
  `contact_number` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
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

insert  into `cpms_system_tenant`(`tenant_id`,`tenant_name`,`contacts`,`contact_number`,`address`,`lease_time_start`,`lease_time_end`,`account_prefix`,`tenant_status`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,'深圳巨人科技服务有限公司','刘先生','15889745718','深圳','2021-09-17 16:41:53','2022-12-17 16:41:53','CS',0,0,'2021-06-13 16:11:43','2021-09-28 12:06:49','1','1'),
(2,'深圳梦想科技有限公司','赵先生','15589745664','深圳','2021-09-17 16:41:53','2022-12-17 16:41:53','MX',0,0,'2021-06-13 16:11:43','2021-09-28 12:06:53','1','1'),
(1442414898631729154,'深圳爱美造型时尚有限公司','王先生','1589545645','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','AM',0,0,'2021-09-27 17:04:45','2021-09-28 12:07:01','000001','000001'),
(1442429609494523905,'深圳美容资讯有限公司','王先生','1589545645','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','ZX',0,0,'2021-09-27 18:03:13','2021-09-28 12:07:09','000001','000001'),
(1442702604909752321,'深圳发型改造有限公司','王先生','1589545645','深圳南山区','2021-06-13 16:11:43','2022-06-13 16:11:43','FX',0,0,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001');

/*Table structure for table `cpms_system_top_menu` */

DROP TABLE IF EXISTS `cpms_system_top_menu`;

CREATE TABLE `cpms_system_top_menu` (
  `top_menu_id` bigint(64) unsigned NOT NULL,
  `tenant_id` bigint(64) NOT NULL COMMENT '租户ID',
  `top_menu_name` varchar(50) NOT NULL DEFAULT '' COMMENT '顶部菜单名称',
  `path` varchar(100) NOT NULL DEFAULT '' COMMENT 'url路径',
  `relation_menu_ids` text COMMENT '关联菜单ID',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) NOT NULL COMMENT '创建人工号',
  `update_by` varchar(50) NOT NULL COMMENT '更新人工号',
  PRIMARY KEY (`top_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顶部菜单表';

/*Data for the table `cpms_system_top_menu` */

insert  into `cpms_system_top_menu`(`top_menu_id`,`tenant_id`,`top_menu_name`,`path`,`relation_menu_ids`,`sort`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,1,'监控系统','','1,7',0,0,'2021-08-30 11:29:38','2021-08-31 14:22:39','000002','000002'),
(2,2,'工作台','','1,2,7',1,0,'2021-08-30 11:32:23','2021-08-31 14:22:42','000002','000002');

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

insert  into `cpms_system_user`(`user_id`,`tenant_id`,`dept_id`,`post_id`,`user_account`,`user_password`,`user_name`,`user_status`,`user_avatar`,`user_sex`,`user_mobile`,`del_flag`,`user_login_ip`,`last_login_time`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,1,2,2,'000001','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','superAdmin',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,'127.0.0.1','2020-05-17 14:27:19','2021-06-13 16:15:34','2021-09-27 10:54:12','000001','000001'),
(2,2,3,0,'000002','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','Mr.Liu',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',0,'15889745719',0,'127.0.0.1','2020-05-15 14:27:50','2021-06-13 16:15:34','2021-09-14 16:57:17','000001','000001'),
(1437695137926684674,2,2,0,'test001','$2a$10$/IgTGoZ91/umYFdLLhnFEumn6Bd9B7JCMBcmbjQ0EAIxRngScEFvS','test001',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-15 19:35:07','2021-09-14 16:30:07','2021-09-15 19:35:09','000001','000001'),
(1437696509359235073,1,2,2,'test002','$2a$10$6DOwEXYOWCTQzyrezO2BlOQqbEN648S66qhh0xZPuSB1eLg4tLrY2','6666',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-15 19:35:04','2021-09-14 16:35:34','2021-09-27 10:54:20','000001','000001'),
(1437698974959869953,1,2,1,'test003','$2a$10$AYAhHljph7h8om9EPP.X8.gUgRPNkoi55MwTh7zS/6hVBHkaPoNs.','test001',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-21 19:35:42','2021-09-14 16:45:21','2021-09-27 10:14:16','000001','000001'),
(1438021853006004225,1,2,1,'test00225','$2a$10$pNh5tZW7B5Ljx1Y5bXu4LugI5.6dK9HwoObw4YSi.NlAnX6vGbDrq','test001',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-15 19:35:09','2021-09-15 14:08:22','2021-09-27 10:14:18','000001','000001'),
(1438055567887011842,1,2,1,'TTTT','$2a$10$9JtVQQ7NZfDfY12s.7mvIewWptl2OIwghmHrGxLDZdlQrekKE52Gu','999',0,'https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,NULL,'2021-09-24 19:35:37','2021-09-15 16:22:20','2021-09-27 10:14:23','000001','000001'),
(1442429609800708097,1442429609494523905,1442429609511301122,NULL,'admin','$2a$10$OCTcvriWiqhIXUFEzVuRe.JUYWR30Bdu8BnXuLxQUMul3Y9nIci86','王先生',0,NULL,0,'1589545645',0,NULL,NULL,'2021-09-27 18:03:13','2021-09-27 18:03:13','000001','000001'),
(1442702605840887809,1442702604909752321,1442702604972666882,NULL,'FX000001','$2a$10$48vhg0DKnMc.Jfs0jAT2ouHgab3Ce0/O7QTRSK8BAB3KiFbIY.mla','王先生',0,NULL,0,'1589545645',0,NULL,NULL,'2021-09-28 12:08:00','2021-09-28 12:08:00','000001','000001');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;