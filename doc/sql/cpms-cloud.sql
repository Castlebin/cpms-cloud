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
  `dept_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `tenant_id` int(11) NOT NULL COMMENT '所属租户ID',
  `dept_name` varchar(50) NOT NULL COMMENT '部门名称',
  `dept_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:正常，1:已禁用，2:删除',
  `parent_id` int(11) DEFAULT NULL COMMENT '树形结构，父节点id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `update_by` varchar(50) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='租户部门关系表';

/*Data for the table `cpms_system_dept` */

insert  into `cpms_system_dept`(`dept_id`,`tenant_id`,`dept_name`,`dept_status`,`parent_id`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,'系统服务总部',0,0,'2021-06-13 16:13:50','2021-06-13 16:15:49','1','1'),(2,1,'研发部',0,1,'2021-06-13 16:13:50','2021-06-13 16:15:52','1','1'),(3,2,'深圳梦想科技有限公司',0,0,'2021-06-13 16:13:50','2021-06-13 16:15:56','1','1');

/*Table structure for table `cpms_system_log` */

DROP TABLE IF EXISTS `cpms_system_log`;

CREATE TABLE `cpms_system_log` (
  `log_id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '日志类型(0-正常 1-异常)',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '日志标题',
  `service_name` varchar(50) DEFAULT NULL COMMENT '服务ID',
  `handle_ip` varchar(255) NOT NULL COMMENT '操作人IP地址',
  `request_url` varchar(255) NOT NULL COMMENT '请求URI',
  `method` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的参数',
  `exe_time` varchar(100) DEFAULT NULL COMMENT '执行时间(毫秒)',
  `exception_msg` text COMMENT '异常信息',
  `log_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:正常，1:删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`log_id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_url`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台系统操作日志表';

/*Data for the table `cpms_system_log` */

/*Table structure for table `cpms_system_menu` */

DROP TABLE IF EXISTS `cpms_system_menu`;

CREATE TABLE `cpms_system_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `permission_code` varchar(50) DEFAULT NULL COMMENT '菜单权限标识编码',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(128) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '排序值',
  `menu_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:正常，1:已禁用，2:删除',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '菜单类型（0菜单 1按钮）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `cpms_system_menu` */

/*Table structure for table `cpms_system_role` */

DROP TABLE IF EXISTS `cpms_system_role`;

CREATE TABLE `cpms_system_role` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `tenant_id` int(11) NOT NULL COMMENT '租户ID',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `role_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:正常，1:已禁用，2:删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

/*Data for the table `cpms_system_role` */

insert  into `cpms_system_role`(`role_id`,`role_name`,`role_code`,`tenant_id`,`role_desc`,`role_status`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'系统超级管理员','SUPER_ADMINISTRATOR',1,NULL,0,'2021-06-13 16:08:23','2021-06-13 16:20:45','1','1'),(2,'总经理',NULL,2,NULL,0,'2021-06-13 16:08:23','2021-06-13 16:08:49','1','1');

/*Table structure for table `cpms_system_role_menu` */

DROP TABLE IF EXISTS `cpms_system_role_menu`;

CREATE TABLE `cpms_system_role_menu` (
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cpms_system_role_menu` */

/*Table structure for table `cpms_system_role_user` */

DROP TABLE IF EXISTS `cpms_system_role_user`;

CREATE TABLE `cpms_system_role_user` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色用户关系表';

/*Data for the table `cpms_system_role_user` */

insert  into `cpms_system_role_user`(`user_id`,`role_id`) values (1,1),(2,2);

/*Table structure for table `cpms_system_tenant` */

DROP TABLE IF EXISTS `cpms_system_tenant`;

CREATE TABLE `cpms_system_tenant` (
  `tenant_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '租户ID，所有的用户都会涉及到这个id',
  `tenant_name` varchar(50) DEFAULT NULL COMMENT '租户名称',
  `tenant_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:正常，1:已禁用，2:删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='租户表';

/*Data for the table `cpms_system_tenant` */

insert  into `cpms_system_tenant`(`tenant_id`,`tenant_name`,`tenant_status`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,'系统服务总部',0,'2021-06-13 16:11:43','2021-06-13 16:12:04','1','1'),(2,'深圳梦想科技有限公司',0,'2021-06-13 16:11:43','2021-06-13 16:12:07','1','1');

/*Table structure for table `cpms_system_user` */

DROP TABLE IF EXISTS `cpms_system_user`;

CREATE TABLE `cpms_system_user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tenant_id` int(11) NOT NULL COMMENT '所属租户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '所属部门ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '用户密码',
  `user_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:正常，1:已禁用，2:删除',
  `user_login_ip` varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '登录IP地址',
  `last_login_time` datetime NOT NULL COMMENT '最后一次登录时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统管理用户表';

/*Data for the table `cpms_system_user` */

insert  into `cpms_system_user`(`user_id`,`tenant_id`,`dept_id`,`user_name`,`user_password`,`user_status`,`user_login_ip`,`last_login_time`,`create_time`,`update_time`,`create_by`,`update_by`) values (1,1,2,'superAdmin','123456',0,'127.0.0.1','2020-05-17 14:27:19','2021-06-13 16:15:34','2021-06-13 16:16:01','1','1'),(2,2,3,'Mr.Liu','123456',0,'127.0.0.1','2020-05-15 14:27:50','2021-06-13 16:15:34','2021-06-13 16:16:05','1','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
