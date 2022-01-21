DROP TABLE IF EXISTS `cpms_system_dept`;

CREATE TABLE `cpms_system_dept` (
  `dept_id` bigint(64) unsigned NOT NULL COMMENT '部门ID',
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `dept_name` varchar(50) NOT NULL COMMENT '部门名称',
  `dept_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '部门描述',
  `dept_sort` int(11) DEFAULT '0' COMMENT '部门排序',
  `parent_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '树形结构，父节点id',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`),
  KEY `tenant_del_dept_idx` (`tenant_id`,`del_flag`,`dept_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

/*Data for the table `cpms_system_dept` */

insert  into `cpms_system_dept`(`dept_id`,`tenant_id`,`dept_name`,`dept_desc`,`dept_sort`,`parent_id`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1442414898812084225,1442414898631729154,'Cpms软件服务工作室总部','Cpms软件服务工作室总部',3,0,0,'2021-06-13 16:13:50','2021-12-23 19:40:25','CS888888','CS888888'),(1442429609511301122,1442414898631729154,'Cpms系统研发中心','cpms软件研发部中心',0,1442414898812084225,0,'2021-06-13 16:13:50','2021-11-26 17:59:58','CS888888','CS888888'),(1473981093762367490,1473981093686870018,'梦想工作室总部','',0,0,0,'2021-12-23 19:37:33','2021-12-23 19:37:33','CS888888','CS888888');

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
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_by` varchar(50) DEFAULT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台系统操作日志表';

/*Data for the table `cpms_system_log` */

insert  into `cpms_system_log`(`log_id`,`tenant_id`,`title`,`service_name`,`handle_ip`,`req_url`,`req_method`,`req_params`,`exe_time`,`result_msg`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`) values (1466994565075439618,1442414898631729154,'修改用户','cpms-server','127.0.0.1','/cpmsService/sys-user/update','POST','{\"userSex\":\"1\",\"userPassword\":\"****\",\"roleIds\":[\"1464171409403650049\"],\"userMobile\":15811111112,\"userRealName\":\"admin\",\"userAccount\":\"CS000002\",\"tenantId\":\"1442414898631729154\",\"deptId\":\"1442429609511301122\",\"postId\":\"1464171813491286018\",\"userName\":\"admin\",\"userId\":\"1464172420088307713\",\"userBirthday\":\"1991-07-03\"}',678,'{\"code\":20008,\"date\":\"2021-12-04 12:55:34\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"b3f058e21a1b44469d580a3cb8e161ac\"}',0,'CS888888','2021-12-04 12:55:34',NULL,'2021-12-04 12:55:34'),(1473101616396173313,1442414898631729154,'修改用户状态','cpms-server','183.62.127.17','/cpmsService/sys-user/changeUserStatus','GET','userId=1464172420088307713&userStatus=1',129,'{\"code\":20008,\"date\":\"2021-12-21 09:22:48\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"e28980a4010343b7b54462064778fd0d\"}',0,'CS888888','2021-12-21 09:22:49',NULL,'2021-12-21 09:22:49'),(1473101624184995841,1442414898631729154,'修改用户状态','cpms-server','183.62.127.17','/cpmsService/sys-user/changeUserStatus','GET','userId=1464172420088307713&userStatus=0',26,'{\"code\":20008,\"date\":\"2021-12-21 09:22:50\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"8fd845f8b04847ba8e0e7354af2678cb\"}',0,'CS888888','2021-12-21 09:22:51',NULL,'2021-12-21 09:22:51'),(1473101646221869058,1442414898631729154,'修改用户','cpms-server','183.62.127.17','/cpmsService/sys-user/update','POST','{\"userSex\":\"1\",\"userPassword\":\"****\",\"roleIds\":[\"1464171409403650049\"],\"userMobile\":15811111112,\"userRealName\":\"admin\",\"userAccount\":\"CS000002\",\"tenantId\":\"1442414898631729154\",\"deptId\":\"1442429609511301122\",\"postId\":\"1464171813491286018\",\"userName\":\"admin\",\"userId\":\"1464172420088307713\",\"userBirthday\":\"1991-07-03\"}',243,'{\"code\":20008,\"date\":\"2021-12-21 09:22:56\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"f6038bf4c64b4ed089e24ee4109efcd4\"}',0,'CS888888','2021-12-21 09:22:56',NULL,'2021-12-21 09:22:56'),(1473647073987985409,1442414898631729154,'修改租户','cpms-server','120.229.32.91','/cpmsService/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳南山区\",\"tenantDesc\":\"Cpms系统软件开发，致力服务于企业软件开发，提供各种互联网软件服务\",\"accountPrefix\":\"CS\",\"updateTime\":\"2021-11-26 17:50:03\",\"tenantCode\":\"CPMS_HEADQUARTERS\",\"leaseTimeEnd\":\"2100-01-01 16:41:53\",\"createBy\":\"CS888888\",\"tenantName\":\"Cpms软件服务有限公司\",\"createTime\":\"2021-06-13 16:11:43\",\"updateBy\":\"CS888888\",\"tenantId\":\"1442414898631729154\",\"contactNumber\":15811111111,\"leaseTimeStart\":\"2021-09-17 16:41:53\",\"contacts\":\"cpms\"}',174,'{\"code\":20008,\"date\":\"2021-12-22 21:30:16\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"1c3030df96924971a3f10561f0b1e4ed\"}',0,'CS888888','2021-12-22 21:30:16',NULL,'2021-12-22 21:30:16'),(1473977399566204930,1442414898631729154,'修改用户','cpms-server','120.229.32.91','/cpmsService/sys-user/update','POST','{\"userSex\":\"1\",\"userPassword\":\"****\",\"roleIds\":[\"1464171409403650049\"],\"userMobile\":15811111112,\"userRealName\":\"admin\",\"userAccount\":\"CS000002\",\"tenantId\":\"1442414898631729154\",\"deptId\":\"1442429609511301122\",\"postId\":\"1464171813491286018\",\"userName\":\"admin\",\"userId\":\"1464172420088307713\",\"userBirthday\":\"1991-07-03\"}',76,'{\"code\":20008,\"date\":\"2021-12-23 19:22:51\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"5e1df1dc4ff74619893bd2e5c646cbe8\"}',0,'CS888888','2021-12-23 19:22:52',NULL,'2021-12-23 19:22:52'),(1473981095125516289,1442414898631729154,'新增租户','cpms-server','120.229.32.91','/cpmsService/sys-tenant/add','POST','{\"address\":\"梦幻空间101房\",\"tenantName\":\"梦想工作室\",\"tenantDesc\":\"111\",\"contactNumber\":17181592356,\"accountPrefix\":\"MX\",\"tenantCode\":\"MEN_XIANG_GONG_ZUO_SHI\",\"leaseTimeStart\":\"2021-12-01 23:59:59\",\"contacts\":\"Dream\",\"leaseTimeEnd\":\"2099-01-01 23:59:59\"}',409,'{\"code\":20000,\"date\":\"2021-12-23 19:37:32\",\"message\":\"响应成功\",\"success\":true,\"traceId\":\"ab9e0d7034f5422c8e2569ee992bf5cb\"}',0,'CS888888','2021-12-23 19:37:33',NULL,'2021-12-23 19:37:33'),(1473981329402560513,1442414898631729154,'改变租户状态','cpms-server','120.229.32.91','/cpmsService/sys-tenant/changeTenantStatus','GET','tenantId=1473981093686870018&tenantStatus=1',65,'{\"code\":20008,\"date\":\"2021-12-23 19:38:28\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"5b838f51a1cc413fa4da11c07ca6eb7c\"}',0,'CS888888','2021-12-23 19:38:29',NULL,'2021-12-23 19:38:29'),(1473981337057165314,1442414898631729154,'改变租户状态','cpms-server','120.229.32.91','/cpmsService/sys-tenant/changeTenantStatus','GET','tenantId=1473981093686870018&tenantStatus=0',10,'{\"code\":20008,\"date\":\"2021-12-23 19:38:30\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"489d73aeddaf4fc991755b09a233239c\"}',0,'CS888888','2021-12-23 19:38:31',NULL,'2021-12-23 19:38:31'),(1473981539134537729,1442414898631729154,'修改租户','cpms-server','120.229.32.91','/cpmsService/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"梦幻空间101房\",\"tenantDesc\":\"有梦想，方能创造未来！！！\",\"accountPrefix\":\"MX\",\"updateTime\":\"2021-12-23 19:37:33\",\"tenantCode\":\"MEN_XIANG_GONG_ZUO_SHI\",\"leaseTimeEnd\":\"2099-01-01 23:59:59\",\"createBy\":\"CS888888\",\"tenantName\":\"梦想工作室\",\"createTime\":\"2021-12-23 19:37:33\",\"updateBy\":\"CS888888\",\"tenantId\":\"1473981093686870018\",\"contactNumber\":17181592356,\"leaseTimeStart\":\"2021-12-01 23:59:59\",\"contacts\":\"Dream\"}',27,'{\"code\":20008,\"date\":\"2021-12-23 19:39:18\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"76d3d5dd14d940dd8c0075b4795938de\"}',0,'CS888888','2021-12-23 19:39:19',NULL,'2021-12-23 19:39:19'),(1473981703182155778,1442414898631729154,'修改租户','cpms-server','120.229.32.91','/cpmsService/sys-tenant/update','POST','{\"tenantStatus\":\"0\",\"address\":\"深圳南山区\",\"tenantDesc\":\"Cpms系统软件开发，致力服务于企业软件开发，提供各种互联网软件服务\",\"accountPrefix\":\"CS\",\"updateTime\":\"2021-12-22 21:30:16\",\"tenantCode\":\"CPMS_HEADQUARTERS\",\"leaseTimeEnd\":\"2100-01-01 16:41:53\",\"createBy\":\"CS888888\",\"tenantName\":\"Cpms软件服务工作室\",\"createTime\":\"2021-06-13 16:11:43\",\"updateBy\":\"CS888888\",\"tenantId\":\"1442414898631729154\",\"contactNumber\":15811111111,\"leaseTimeStart\":\"2021-09-17 16:41:53\",\"contacts\":\"cpms\"}',29,'{\"code\":20008,\"date\":\"2021-12-23 19:39:57\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"dfd431941d5f4cc08ce4b652ac89dcd5\"}',0,'CS888888','2021-12-23 19:39:58',NULL,'2021-12-23 19:39:58'),(1473981817846038530,1442414898631729154,'修改部门','cpms-server','120.229.32.91','/cpmsService/sys-dept/update','POST','{\"deptSort\":3,\"deptName\":\"Cpms软件服务工作室总部\",\"deptId\":\"1442414898812084225\",\"deptDesc\":\"Cpms软件服务工作室总部\",\"parentId\":\"0\"}',109,'{\"code\":20008,\"date\":\"2021-12-23 19:40:25\",\"message\":\"操作成功\",\"success\":true,\"traceId\":\"c836f08608554f788a8b4c67d61240cf\"}',0,'CS888888','2021-12-23 19:40:25',NULL,'2021-12-23 19:40:25');

/*Table structure for table `cpms_system_menu` */

DROP TABLE IF EXISTS `cpms_system_menu`;

CREATE TABLE `cpms_system_menu` (
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID',
  `parent_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '父菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单|按钮名称',
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(128) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序值',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '菜单类型:0-菜单，1-按钮',
  `open_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否新窗口打开:0-不是，1-是',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`menu_id`,`type`,`open_flag`,`del_flag`),
  UNIQUE KEY `code_path` (`code`,`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

/*Data for the table `cpms_system_menu` */

insert  into `cpms_system_menu`(`menu_id`,`parent_id`,`name`,`code`,`alias`,`path`,`icon`,`component`,`sort`,`type`,`open_flag`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1437695137926684674,0,'系统管理','sys_system_manage','menu','/system','el-icon-setting',NULL,1000000,0,0,0,'2021-08-07 15:42:40','2021-11-26 17:38:33','CS888888','CS888888'),(1437696509359235073,1437695137926684674,'用户管理','sys_user_manage','menu','/system/user','el-icon-user-solid',NULL,999993,0,0,0,'2021-08-07 15:45:10','2021-11-26 17:41:07','CS888888','CS888888'),(1437698974959869953,1437696509359235073,'查看','sys_user_view','view','','',NULL,4,1,0,0,'2021-08-07 15:46:57','2021-11-26 17:40:54','CS888888','CS888888'),(1438021853006004225,1437696509359235073,'删除','sys_user_delete','delete','','',NULL,1,1,0,0,'2021-08-07 15:47:41','2021-11-26 17:40:49','CS888888','CS888888'),(1438055567887011842,1437696509359235073,'添加','sys_user_add','add','','',NULL,3,1,0,0,'2021-08-07 18:15:39','2021-11-26 17:40:46','CS888888','CS888888'),(1442429609519689729,1442702605840887809,'查看','sys_menu_view','view','','',NULL,4,1,0,0,'2021-08-08 11:53:55','2021-11-26 17:40:33','CS888888','CS888888'),(1442429609800708097,1437696509359235073,'编辑','sys_user_edit','edit','','',NULL,2,1,0,0,'2021-08-07 18:18:10','2021-11-26 17:40:42','CS888888','CS888888'),(1442702604997832705,1442702605840887809,'删除','sys_menu_delete','delete',NULL,NULL,NULL,2,1,0,0,'2021-08-08 11:55:17','2021-11-26 17:40:29','CS888888','CS888888'),(1442702605840887809,1437695137926684674,'菜单管理','sys_menu_manage','menu','/system/menu','el-icon-menu',NULL,999999,0,0,0,'2021-08-08 11:52:10','2021-11-26 17:44:01','CS888888','CS888888'),(1443484962904367106,1437695137926684674,'角色管理','sys_role_manage','menu','/system/role','el-icon-user','',999995,0,0,0,'2021-09-30 15:56:48','2021-11-26 17:38:43','CS888888','CS888888'),(1443771116924960769,1437695137926684674,'租户管理','sys_tenant_manage','menu','/system/tenant','el-icon-s-custom','',999997,0,0,0,'2021-10-01 10:53:53','2021-11-26 17:38:44','CS888888','CS888888'),(1444119091446562817,1442702605840887809,'添加','sys_menu_add','add',NULL,NULL,NULL,3,1,0,0,'2021-08-08 11:56:54','2021-11-26 17:40:25','CS888888','CS888888'),(1444120246532718595,1442702605840887809,'编辑','sys_menu_edit','edit','','',NULL,1,1,0,0,'2021-08-08 11:58:31','2021-11-26 17:40:21','CS888888','CS888888'),(1444133692150136834,1437695137926684674,'部门管理','sys_dept_manage','menu','/system/dept','el-icon-set-up','',999996,0,0,0,'2021-09-30 22:10:32','2021-11-26 17:40:15','CS888888','CS888888'),(1449236520891928577,1437695137926684674,'岗位管理','sys_post_manage','menu','/system/post','el-icon-postcard','',999994,0,0,0,'2021-10-16 12:51:27','2021-11-26 17:38:45','CS888888','CS888888'),(1454301889629257730,1437695137926684674,'顶部菜单','sys_top_menu_manage','menu','/system/topMenu','el-icon-news','',999998,0,1,0,'2021-10-30 12:19:25','2021-11-26 17:38:53','CS888888','CS888888'),(1458030738464677889,0,'监控管理','sys_monitor_manage','menu','/monitor','el-icon-s-data',NULL,0,0,0,0,'2021-11-09 19:16:32','2021-11-25 21:34:52','CS888888','CS888888'),(1458031183908151297,1458030738464677889,'系统日志','sys_log_manage','menu','/system/log','el-icon-monitor',NULL,0,0,0,0,'2021-11-09 19:18:18','2021-11-21 16:06:38','CS888888','CS888888'),(1459864232203497474,1443771116924960769,'添加','sys_tenant_add','add','','',NULL,3,1,0,0,'2021-11-14 20:42:11','2021-11-14 20:46:13','CS888888','CS888888'),(1459864504149585922,1443771116924960769,'删除','sys_tenant_delete','delete','','',NULL,1,1,0,0,'2021-11-14 20:43:15','2021-11-14 20:46:28','CS888888','CS888888'),(1459864696802357250,1443771116924960769,'编辑','sys_tenant_edit','edit','','',NULL,2,1,0,0,'2021-11-14 20:44:01','2021-11-14 20:46:23','CS888888','CS888888'),(1459865217164488706,1443771116924960769,'查看','sys_tenant_view','view','','',NULL,4,1,0,0,'2021-11-14 20:46:05','2021-11-14 20:46:05','CS888888','CS888888'),(1459866979745247234,1443771116924960769,'配置租户权限','sys_tenant_config_per','config','','',NULL,0,1,0,0,'2021-11-14 20:53:06','2021-11-14 20:53:06','CS888888','CS888888'),(1459868347348398082,1444133692150136834,'查看','sys_dept_view','view','','',NULL,4,1,0,0,'2021-11-14 20:58:32','2021-11-26 17:39:33','CS888888','CS888888'),(1459868748189642754,1444133692150136834,'添加','sys_dept_add','add','','',NULL,3,1,0,0,'2021-11-14 21:00:07','2021-11-26 17:39:34','CS888888','CS888888'),(1459868918230921217,1444133692150136834,'删除','sys_dept_delete','delete','','',NULL,1,1,0,0,'2021-11-14 21:00:48','2021-11-26 17:39:36','CS888888','CS888888'),(1459869191464660994,1444133692150136834,'编辑','sys_dept_edit','edit','','',NULL,2,1,0,0,'2021-11-14 21:01:53','2021-11-26 17:39:37','CS888888','CS888888'),(1459869624430080001,1443484962904367106,'查看','sys_role_view','view','','',NULL,4,1,0,0,'2021-11-14 21:03:36','2021-11-19 17:31:53','CS888888','CS888888'),(1459869734446673922,1443484962904367106,'添加','sys_role_add','add','','',NULL,3,1,0,0,'2021-11-14 21:04:02','2021-11-14 21:04:02','CS888888','CS888888'),(1459869888272773121,1443484962904367106,'编辑','sys_role_edit','edit','','',NULL,2,1,0,0,'2021-11-14 21:04:39','2021-11-14 21:04:39','CS888888','CS888888'),(1459869986729865217,1443484962904367106,'删除','sys_role_delete','delete','','',NULL,1,1,0,0,'2021-11-14 21:05:03','2021-11-14 21:06:37','CS888888','CS888888'),(1459870315957563393,1443484962904367106,'配置角色权限','sys_role_config_per','config','','',NULL,0,1,0,0,'2021-11-14 21:06:21','2021-11-14 21:06:21','CS888888','CS888888'),(1459870568081371137,1449236520891928577,'查看','sys_post_view','view','','',NULL,4,1,0,0,'2021-11-14 21:07:21','2021-11-14 21:07:21','CS888888','CS888888'),(1459870681822507009,1449236520891928577,'添加','sys_post_add','add','','',NULL,3,1,0,0,'2021-11-14 21:07:48','2021-11-14 21:07:48','CS888888','CS888888'),(1459870827058671617,1449236520891928577,'编辑','sys_post_edit','edit','','',NULL,2,1,0,0,'2021-11-14 21:08:23','2021-11-14 21:08:23','CS888888','CS888888'),(1459870934340579330,1449236520891928577,'删除','sys_post_delete','delete','','',NULL,1,1,0,0,'2021-11-14 21:08:49','2021-11-14 21:08:49','CS888888','CS888888'),(1459871981415018497,1437696509359235073,'修改用户密码','sys_user_modified_pwd','pwd','','',NULL,0,1,0,0,'2021-11-14 21:12:58','2021-11-26 17:39:32','CS888888','CS888888');

/*Table structure for table `cpms_system_post` */

DROP TABLE IF EXISTS `cpms_system_post`;

CREATE TABLE `cpms_system_post` (
  `post_id` bigint(64) NOT NULL COMMENT '岗位ID',
  `tenant_id` bigint(64) NOT NULL COMMENT '所属租户ID',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_code` varchar(30) NOT NULL DEFAULT '' COMMENT '岗位编码',
  `post_sort` int(11) NOT NULL DEFAULT '0' COMMENT '岗位排序',
  `post_desc` varchar(1000) NOT NULL DEFAULT '' COMMENT '岗位描述',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `tenant_id_post_code` (`tenant_id`,`post_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位表';

/*Data for the table `cpms_system_post` */

insert  into `cpms_system_post`(`post_id`,`tenant_id`,`post_name`,`post_code`,`post_sort`,`post_desc`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1450767760723918849,1442414898631729154,'总经理','CEO',20,'公司总经理',0,'2021-10-20 18:16:03','2021-11-26 17:21:53','CS888888','CS888888'),(1464171813491286018,1442414898631729154,'首席技术官','CTO',4,'系统架构设计',0,'2021-11-26 17:58:58','2021-11-26 17:59:20','CS888888','CS888888');

/*Table structure for table `cpms_system_role` */

DROP TABLE IF EXISTS `cpms_system_role`;

CREATE TABLE `cpms_system_role` (
  `role_id` bigint(64) unsigned NOT NULL COMMENT '角色id',
  `tenant_id` bigint(64) NOT NULL COMMENT '租户ID',
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `role_desc` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `role_sort` int(11) NOT NULL DEFAULT '0' COMMENT '角色排序',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `tanant_id_role_code` (`tenant_id`,`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户角色表';

/*Data for the table `cpms_system_role` */

insert  into `cpms_system_role`(`role_id`,`tenant_id`,`role_name`,`role_code`,`role_desc`,`role_sort`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1464156988723798018,1442414898631729154,'系统超级管理员','SUPER_ADMINISTRATOR','系统超级管理员',10000,0,'2021-06-13 16:08:23','2021-11-26 17:22:21','CS888888','CS888888'),(1464171409403650049,1442414898631729154,'管理员','ADMINISTRATOR','系统管理员',0,0,'2021-11-26 17:57:22','2021-11-26 17:57:22','CS888888','CS888888'),(1473981093816893441,1473981093686870018,'管理员','TENANT_ADMINISTRATOR','租户管理员角色',10000,0,'2021-12-23 19:37:33','2021-12-23 19:37:33','CS888888','CS888888');

/*Table structure for table `cpms_system_role_menu` */

DROP TABLE IF EXISTS `cpms_system_role_menu`;

CREATE TABLE `cpms_system_role_menu` (
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(64) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';

/*Data for the table `cpms_system_role_menu` */

insert  into `cpms_system_role_menu`(`role_id`,`menu_id`) values (1464171409403650049,1437695137926684674),(1464171409403650049,1442702605840887809),(1464171409403650049,1442429609519689729),(1464171409403650049,1444119091446562817),(1464171409403650049,1442702604997832705),(1464171409403650049,1444120246532718595),(1464171409403650049,1454301889629257730),(1464171409403650049,1443771116924960769),(1464171409403650049,1459865217164488706),(1464171409403650049,1459864232203497474),(1464171409403650049,1459864696802357250),(1464171409403650049,1459864504149585922),(1464171409403650049,1459866979745247234),(1464171409403650049,1444133692150136834),(1464171409403650049,1459868347348398082),(1464171409403650049,1459868748189642754),(1464171409403650049,1459869191464660994),(1464171409403650049,1459868918230921217),(1464171409403650049,1443484962904367106),(1464171409403650049,1459869624430080001),(1464171409403650049,1459869734446673922),(1464171409403650049,1459869888272773121),(1464171409403650049,1459869986729865217),(1464171409403650049,1459870315957563393),(1464171409403650049,1449236520891928577),(1464171409403650049,1459870568081371137),(1464171409403650049,1459870681822507009),(1464171409403650049,1459870827058671617),(1464171409403650049,1459870934340579330),(1464171409403650049,1437696509359235073),(1464171409403650049,1437698974959869953),(1464171409403650049,1438055567887011842),(1464171409403650049,1442429609800708097),(1464171409403650049,1438021853006004225),(1464171409403650049,1459871981415018497),(1464171409403650049,1458030738464677889),(1464171409403650049,1458031183908151297);

/*Table structure for table `cpms_system_role_user` */

DROP TABLE IF EXISTS `cpms_system_role_user`;

CREATE TABLE `cpms_system_role_user` (
  `user_id` bigint(64) NOT NULL COMMENT '用户ID',
  `role_id` bigint(64) NOT NULL COMMENT '角色ID',
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色用户关系表';

/*Data for the table `cpms_system_role_user` */

insert  into `cpms_system_role_user`(`user_id`,`role_id`) values (1437695137926684674,1464156988723798018),(1464172420088307713,1464171409403650049),(1473981094655754241,1473981093816893441);

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
  `tenant_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-正常，1-已禁用',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0:未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`tenant_id`),
  UNIQUE KEY `tenant_code` (`tenant_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户表';

/*Data for the table `cpms_system_tenant` */

insert  into `cpms_system_tenant`(`tenant_id`,`tenant_name`,`tenant_code`,`contacts`,`contact_number`,`tenant_desc`,`address`,`lease_time_start`,`lease_time_end`,`account_prefix`,`tenant_status`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1442414898631729154,'Cpms软件服务工作室','CPMS_HEADQUARTERS','cpms','15811111111','Cpms系统软件开发，致力服务于企业软件开发，提供各种互联网软件服务','深圳南山区','2021-09-17 16:41:53','2100-01-01 16:41:53','CS',0,0,'2021-06-13 16:11:43','2021-12-23 19:39:58','CS888888','CS888888'),(1473981093686870018,'梦想工作室','MEN_XIANG_GONG_ZUO_SHI','Dream','17181592356','有梦想，方能创造未来！！！','梦幻空间101房','2021-12-01 23:59:59','2099-01-01 23:59:59','MX',0,0,'2021-12-23 19:37:33','2021-12-23 19:39:19','CS888888','CS888888');

/*Table structure for table `cpms_system_top_menu` */

DROP TABLE IF EXISTS `cpms_system_top_menu`;

CREATE TABLE `cpms_system_top_menu` (
  `top_menu_id` bigint(64) unsigned NOT NULL,
  `user_id` bigint(64) NOT NULL COMMENT '用户userID',
  `top_menu_name` varchar(50) NOT NULL DEFAULT '' COMMENT '顶部菜单名称',
  `path` varchar(100) NOT NULL DEFAULT '' COMMENT '外链url',
  `relation_menu_ids` text COMMENT '关联菜单ID',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-菜单，1-外链',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人工号',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人工号',
  PRIMARY KEY (`top_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='顶部菜单表';

/*Data for the table `cpms_system_top_menu` */

insert  into `cpms_system_top_menu`(`top_menu_id`,`user_id`,`top_menu_name`,`path`,`relation_menu_ids`,`icon`,`type`,`sort`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1473979463801307137,1437695137926684674,'系统监控','','1458030738464677889,1458031183908151297','el-icon-data-line',0,0,0,'2021-12-23 19:31:04','2021-12-23 11:31:24','CS888888','CS888888');

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
  `user_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-正常，1-已禁用',
  `user_avatar` varchar(2000) DEFAULT NULL COMMENT '用户头像',
  `user_sex` tinyint(3) NOT NULL DEFAULT '0' COMMENT '用户性别：0-未知，1-男，2-女',
  `user_birthday` date DEFAULT NULL COMMENT '用户生日日期',
  `user_mobile` varchar(50) DEFAULT NULL COMMENT '用户手机号',
  `sys_data` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否系统数据: 0-不是，1-是',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `user_login_ip` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '登录IP地址',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account_idex` (`user_account`),
  KEY `tenant_dept_post_idx` (`tenant_id`,`dept_id`,`post_id`),
  KEY `user_name_idex` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统管理用户表';

/*Data for the table `cpms_system_user` */

insert  into `cpms_system_user`(`user_id`,`tenant_id`,`dept_id`,`post_id`,`user_account`,`user_password`,`user_name`,`user_real_name`,`user_status`,`user_avatar`,`user_sex`,`user_birthday`,`user_mobile`,`sys_data`,`del_flag`,`user_login_ip`,`last_login_time`,`create_time`,`update_time`,`create_by`,`update_by`) values (1437695137926684674,1442414898631729154,1442414898812084225,1450767760723918849,'CS888888','$2a$10$2sQ90igwAqQhEiigMhEjwe4NN8ASSVuEAGRMbbA4GLY1OCZq2nHKG','superAdmin','cpms',0,'https://iconfont.alicdn.com/t/9abd4acd-0b67-4b63-9163-205238a6aab4.png',1,'1991-07-03','15811111111',1,0,'219.143.121.29','2022-01-21 13:54:08','2021-06-13 16:15:34','2022-01-21 05:54:08','CS888888','CS888888'),(1464172420088307713,1442414898631729154,1442429609511301122,1464171813491286018,'CS000002','$2a$10$Dz9NBao9g2XWkTb0A/QXbOuitWJBS6g13qnUPxu.Ky0A.t0PEfvay','admin','admin',0,NULL,1,'1991-07-03','15811111112',0,0,'14.108.214.163','2022-01-06 10:49:53','2021-11-26 18:01:23','2022-01-06 02:49:52','CS888888','CS888888'),(1473981094655754241,1473981093686870018,1473981093762367490,NULL,'MX000001','$2a$10$/t.agL3Tn4Zp0aDRxAtfg.X8wIe1qEWWox1y4Cp/kLgd4YVpf1Gba','Dream','Dream',0,NULL,0,NULL,'17181592356',1,0,NULL,NULL,'2021-12-23 19:37:33','2021-12-23 19:37:33','CS888888','CS888888');

