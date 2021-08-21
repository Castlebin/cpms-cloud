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

CREATE TABLE `cpms_system_dept` (
  `dept_id` bigint(64) unsigned NOT NULL COMMENT '部门ID',
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `dept_name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '树形结构，父节点id',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户部门关系表';

/*Data for the table `cpms_system_dept` */

insert  into `cpms_system_dept`(`dept_id`,`tenant_id`,`dept_name`,`parent_id`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,1,'深圳巨人科技软件服务有限公司',0,0,'2021-06-13 16:13:50','2021-08-08 14:10:22','1','1'),
(2,1,'Cpms系统研发部',1,0,'2021-06-13 16:13:50','2021-08-06 16:03:28','1','1'),
(3,2,'深圳梦想科技有限公司',0,0,'2021-06-13 16:13:50','2021-06-13 16:15:56','1','1');

/*Table structure for table `cpms_system_log` */

CREATE TABLE `cpms_system_log` (
  `log_id` bigint(64) NOT NULL COMMENT '日志ID',
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台系统操作日志表';

/*Data for the table `cpms_system_log` */

/*Table structure for table `cpms_system_menu` */

CREATE TABLE `cpms_system_menu` (
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID',
  `parent_id` int(11) DEFAULT '0' COMMENT '父菜单ID',
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
(1,0,'系统管理','system_manage','menu',NULL,NULL,NULL,1,0,0,0,'2021-08-07 15:42:40','2021-08-07 15:53:39','000001','000001'),
(2,1,'用户管理','user_manage','menu',NULL,NULL,NULL,2,0,0,0,'2021-08-07 15:45:10','2021-08-07 15:53:34','000001','000001'),
(3,2,'查看','sys_user_view','view',NULL,NULL,NULL,3,1,0,0,'2021-08-07 15:46:57','2021-08-07 15:53:33','000001','000001'),
(4,2,'删除','sys_user_delete','delete',NULL,NULL,NULL,4,1,0,0,'2021-08-07 15:47:41','2021-08-07 15:53:32','000001','000001'),
(5,2,'添加','sys_user_add','add',NULL,NULL,NULL,5,1,0,0,'2021-08-07 18:15:39','2021-08-07 18:16:53','000001','000001'),
(6,2,'编辑','sys_user_edit','edit',NULL,NULL,NULL,6,1,0,0,'2021-08-07 18:18:10','2021-08-07 18:19:35','000001','000001'),
(7,1,'菜单管理','menu_manage','menu',NULL,NULL,NULL,3,0,0,0,'2021-08-08 11:52:10','2021-08-08 11:53:31','000001','000001'),
(8,7,'查看','sys_menu_view','view',NULL,NULL,NULL,1,1,0,0,'2021-08-08 11:53:55','2021-08-08 11:54:56','000001','000001'),
(9,7,'删除','sys_menu_delete','delete',NULL,NULL,NULL,2,1,0,0,'2021-08-08 11:55:17','2021-08-08 11:57:26','000001','000001'),
(10,7,'添加','sys_menu_add','add',NULL,NULL,NULL,3,1,0,0,'2021-08-08 11:56:54','2021-08-08 11:57:48','000001','000001'),
(11,7,'编辑','sys_menu_edit','edit',NULL,NULL,NULL,4,1,0,0,'2021-08-08 11:58:31','2021-08-08 11:59:16','000001','000001');

/*Table structure for table `cpms_system_role` */

CREATE TABLE `cpms_system_role` (
  `role_id` bigint(64) unsigned NOT NULL COMMENT '角色id',
  `tenant_id` bigint(64) NOT NULL COMMENT '租户ID',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

/*Data for the table `cpms_system_role` */

insert  into `cpms_system_role`(`role_id`,`tenant_id`,`role_name`,`role_code`,`role_desc`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,1,'系统超级管理员','SUPER_ADMINISTRATOR',NULL,0,'2021-06-13 16:08:23','2021-08-08 11:32:44','000001','000001'),
(2,2,'管理员','ADMINISTRATOR',NULL,0,'2021-06-13 16:08:23','2021-08-08 11:32:49','000001','000001'),
(3,1,'推广专员','TUI_GUANG',NULL,0,'2021-08-06 17:34:20','2021-08-08 11:32:46','000001','000001');

/*Table structure for table `cpms_system_role_menu` */

CREATE TABLE `cpms_system_role_menu` (
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cpms_system_role_menu` */

insert  into `cpms_system_role_menu`(`role_id`,`menu_id`) values 
(2,7),
(2,1),
(2,8),
(2,9),
(2,5),
(2,6),
(2,4);

/*Table structure for table `cpms_system_role_user` */

CREATE TABLE `cpms_system_role_user` (
  `user_id` bigint(64) NOT NULL COMMENT '用户ID',
  `role_id` bigint(64) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色用户关系表';

/*Data for the table `cpms_system_role_user` */

insert  into `cpms_system_role_user`(`user_id`,`role_id`) values 
(1,1),
(2,2),
(1,3);

/*Table structure for table `cpms_system_tenant` */

CREATE TABLE `cpms_system_tenant` (
  `tenant_id` bigint(64) unsigned NOT NULL COMMENT '租户ID，所有的用户都会涉及到这个id',
  `tenant_name` varchar(50) NOT NULL COMMENT '租户名称',
  `tenant_status` tinyint(3) DEFAULT '0' COMMENT '0-正常，1-已禁用',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0:未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户表';

/*Data for the table `cpms_system_tenant` */

insert  into `cpms_system_tenant`(`tenant_id`,`tenant_name`,`tenant_status`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,'深圳巨人科技服务有限公司',0,0,'2021-06-13 16:11:43','2021-08-06 16:00:11','1','1'),
(2,'深圳梦想科技有限公司',0,0,'2021-06-13 16:11:43','2021-06-13 16:12:07','1','1');

/*Table structure for table `cpms_system_user` */

CREATE TABLE `cpms_system_user` (
  `user_id` bigint(64) unsigned NOT NULL,
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `dept_id` bigint(64) NOT NULL COMMENT '所属部门ID',
  `user_account` varchar(50) NOT NULL COMMENT '用户账号',
  `user_password` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户密码',
  `user_name` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户名',
  `user_status` tinyint(3) DEFAULT '0' COMMENT '0-正常，1-已禁用',
  `user_login_ip` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '登录IP地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `user_avatar` varchar(2000) DEFAULT NULL COMMENT '用户头像',
  `user_sex` tinyint(3) DEFAULT '0' COMMENT '用户性别：0-未知，1-男，2-女',
  `user_mobile` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `del_flag` tinyint(3) DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理用户表';

/*Data for the table `cpms_system_user` */

insert  into `cpms_system_user`(`user_id`,`tenant_id`,`dept_id`,`user_account`,`user_password`,`user_name`,`user_status`,`user_login_ip`,`last_login_time`,`user_avatar`,`user_sex`,`user_mobile`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values 
(1,1,2,'000001','123456','superAdmin',0,'127.0.0.1','2020-05-17 14:27:19','https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',1,'15889745718',0,'2021-06-13 16:15:34','2021-08-07 15:55:28','000001','000001'),
(2,2,3,'000002','123456','Mr.Liu',0,'127.0.0.1','2020-05-15 14:27:50','https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png',0,'15889745719',0,'2021-06-13 16:15:34','2021-08-11 11:25:47','000001','000001');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
