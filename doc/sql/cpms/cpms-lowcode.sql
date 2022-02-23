DROP TABLE IF EXISTS `cpms_code_project`;

CREATE TABLE `cpms_code_project` (
  `project_id` bigint(64) unsigned NOT NULL COMMENT '主键',
  `project_name` varchar(100) NOT NULL COMMENT '项目名称',
  `project_port` int(11) NOT NULL DEFAULT '8080' COMMENT '服务端口',
  `package_name` varchar(100) NOT NULL COMMENT '项目基础包名',
  `file_encoding` varchar(10) NOT NULL DEFAULT 'UTF-8' COMMENT '文件编码',
  `jdk_version` varchar(10) NOT NULL DEFAULT '1.8' COMMENT 'jdk版本',
  `context_path` varchar(100) DEFAULT NULL COMMENT '项目访问路径的上下文',
  `group_id` varchar(50) DEFAULT NULL COMMENT '项目groupId',
  `artifact_id` varchar(50) NOT NULL COMMENT '项目artifactId',
  `version` varchar(20) NOT NULL DEFAULT '1.0.0' COMMENT '项目版本号',
  `project_desc` varchar(500) DEFAULT NULL COMMENT '项目描述',
  `team_name` varchar(100) DEFAULT NULL COMMENT '项目所属团队',
  `leader` varchar(100) DEFAULT NULL COMMENT '项目负责人',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目基础信息配置表';


insert  into `cpms_code_project`(`project_id`,`project_name`,`project_port`,`package_name`,`file_encoding`,`jdk_version`,`context_path`,`group_id`,`artifact_id`,`version`,`project_desc`,`team_name`,`leader`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1492032178107297793,'gencode',8080,'com.cpms.gencode','UTF-8','1.8','/gencode','com.cpms','cpms-gencode','1.0.0','测试数据','','',0,'2022-02-11 15:06:07','2022-02-23 20:10:48','CS888888','CS888888');


DROP TABLE IF EXISTS `cpms_code_project_db`;

CREATE TABLE `cpms_code_project_db` (
  `db_id` bigint(64) unsigned NOT NULL COMMENT '主键',
  `project_id` bigint(64) unsigned NOT NULL COMMENT '所属项目id',
  `db_driver_class` varchar(20) NOT NULL COMMENT '数据库驱动类型：1-MySQL',
  `db_ip` varchar(200) NOT NULL COMMENT '数据库：ip',
  `db_port` int(11) NOT NULL DEFAULT '3306' COMMENT '数据库：端口',
  `db_name` varchar(50) NOT NULL COMMENT '数据库：库名',
  `db_user` varchar(20) NOT NULL COMMENT '数据库：用户名',
  `db_password` varchar(100) NOT NULL COMMENT '数据库：密码',
  `db_primary` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否为项目主库：0-不是，1-是',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`db_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目数据库信息配置表';


insert  into `cpms_code_project_db`(`db_id`,`project_id`,`db_driver_class`,`db_ip`,`db_port`,`db_name`,`db_user`,`db_password`,`db_primary`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1492032178107297795,1492032178107297793,'1','127.0.0.1',3306,'cpms-lowcode','root','root',1,0,'2022-02-23 20:10:48','2022-02-23 20:10:48','CS888888','CS888888');

DROP TABLE IF EXISTS `cpms_code_table`;

CREATE TABLE `cpms_code_table` (
  `table_id` bigint(64) unsigned NOT NULL COMMENT '主键',
  `db_id` bigint(64) unsigned NOT NULL COMMENT '所属数据库id',
  `table_name` varchar(100) NOT NULL COMMENT '表名称',
  `table_comment` varchar(100) DEFAULT NULL COMMENT '表描述',
  `class_name` varchar(100) NOT NULL COMMENT '实体类名',
  `module_name` varchar(100) NOT NULL COMMENT '模块名称',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表';


insert  into `cpms_code_table`(`table_id`,`db_id`,`table_name`,`table_comment`,`class_name`,`module_name`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1496466324544045057,1492032178107297795,'cpms_code_table_column','代码生成业务表字段','CpmsCodeTableColumn','cpmscodetablecolumn',0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324560822274,1492032178107297795,'cpms_code_project','项目基础信息配置表','CpmsCodeProject','cpmscodeproject',0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324560822275,1492032178107297795,'cpms_code_table','代码生成业务表','CpmsCodeTable','cpmscodetable',0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324560822276,1492032178107297795,'cpms_code_project_db','项目数据库信息配置表','CpmsCodeProjectDb','cpmscodeprojectdb',0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888');

DROP TABLE IF EXISTS `cpms_code_table_column`;

CREATE TABLE `cpms_code_table_column` (
  `column_id` bigint(64) unsigned NOT NULL COMMENT '主键',
  `table_id` bigint(64) unsigned NOT NULL COMMENT '归属表id',
  `column_name` varchar(100) NOT NULL COMMENT '列名称',
  `java_field` varchar(100) NOT NULL COMMENT 'JAVA字段名',
  `column_type` varchar(100) NOT NULL COMMENT '列类型',
  `java_type` varchar(100) NOT NULL COMMENT 'JAVA类型',
  `if_pk` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否主键: 0-不是，1-是',
  `if_increment` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否自增: 0-不是，1-是',
  `column_comment` varchar(100) DEFAULT NULL COMMENT '列描述',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `del_flag` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表字段';

insert  into `cpms_code_table_column`(`column_id`,`table_id`,`column_name`,`java_field`,`column_type`,`java_type`,`if_pk`,`if_increment`,`column_comment`,`sort`,`del_flag`,`create_time`,`update_time`,`create_by`,`update_by`) values (1496466324627931138,1496466324560822274,'project_id','projectId','bigint','Long',1,0,'主键',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324627931139,1496466324560822274,'project_name','projectName','varchar','String',0,0,'项目名称',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324627931140,1496466324560822274,'project_port','projectPort','int','Integer',0,0,'服务端口',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324627931141,1496466324560822274,'package_name','packageName','varchar','String',0,0,'项目基础包名',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845697,1496466324560822274,'file_encoding','fileEncoding','varchar','String',0,0,'文件编码',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845698,1496466324560822274,'jdk_version','jdkVersion','varchar','String',0,0,'jdk版本',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845699,1496466324560822274,'context_path','contextPath','varchar','String',0,0,'项目访问路径的上下文',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845700,1496466324560822274,'group_id','groupId','varchar','String',0,0,'项目groupId',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845701,1496466324560822274,'artifact_id','artifactId','varchar','String',0,0,'项目artifactId',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845702,1496466324560822274,'version','version','varchar','String',0,0,'项目版本号',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845703,1496466324560822274,'project_desc','projectDesc','varchar','String',0,0,'项目描述',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324690845704,1496466324560822274,'team_name','teamName','varchar','String',0,0,'项目所属团队',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954561,1496466324560822274,'leader','leader','varchar','String',0,0,'项目负责人',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954562,1496466324560822274,'del_flag','delFlag','tinyint','Integer',0,0,'0-未删除，1-已删除',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954563,1496466324560822274,'create_time','createTime','datetime','LocalDateTime',0,0,'创建时间',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954564,1496466324560822274,'update_time','updateTime','datetime','LocalDateTime',0,0,'更新时间',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954565,1496466324560822274,'create_by','createBy','varchar','String',0,0,'创建人',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954566,1496466324560822274,'update_by','updateBy','varchar','String',0,0,'更新人',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954567,1496466324560822276,'db_id','dbId','bigint','Long',1,0,'主键',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954568,1496466324560822276,'project_id','projectId','bigint','Long',0,0,'所属项目id',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954569,1496466324560822276,'db_driver_class','dbDriverClass','varchar','String',0,0,'数据库驱动类型：1-MySQL',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954570,1496466324560822276,'db_ip','dbIp','varchar','String',0,0,'数据库：ip',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954571,1496466324560822276,'db_port','dbPort','int','Integer',0,0,'数据库：端口',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954572,1496466324560822276,'db_name','dbName','varchar','String',0,0,'数据库：库名',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954573,1496466324560822276,'db_user','dbUser','varchar','String',0,0,'数据库：用户名',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954574,1496466324560822276,'db_password','dbPassword','varchar','String',0,0,'数据库：密码',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324757954575,1496466324560822276,'db_primary','dbPrimary','tinyint','Integer',0,0,'是否为项目主库：0-不是，1-是',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063425,1496466324560822276,'del_flag','delFlag','tinyint','Integer',0,0,'0-未删除，1-已删除',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063426,1496466324560822276,'create_time','createTime','datetime','LocalDateTime',0,0,'创建时间',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063427,1496466324560822276,'update_time','updateTime','datetime','LocalDateTime',0,0,'更新时间',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063428,1496466324560822276,'create_by','createBy','varchar','String',0,0,'创建人',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063429,1496466324560822276,'update_by','updateBy','varchar','String',0,0,'更新人',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063430,1496466324560822275,'table_id','tableId','bigint','Long',1,0,'主键',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063431,1496466324560822275,'db_id','dbId','bigint','Long',0,0,'所属数据库id',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063432,1496466324560822275,'table_name','tableName','varchar','String',0,0,'表名称',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063433,1496466324560822275,'table_comment','tableComment','varchar','String',0,0,'表描述',0,0,'2022-02-23 20:45:49','2022-02-23 20:45:49','CS888888','CS888888'),(1496466324825063434,1496466324560822275,'class_name','className','varchar','String',0,0,'实体类名',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063435,1496466324560822275,'module_name','moduleName','varchar','String',0,0,'模块名称',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063436,1496466324560822275,'del_flag','delFlag','tinyint','Integer',0,0,'0-未删除，1-已删除',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063437,1496466324560822275,'create_time','createTime','datetime','LocalDateTime',0,0,'创建时间',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063438,1496466324560822275,'update_time','updateTime','datetime','LocalDateTime',0,0,'更新时间',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063439,1496466324560822275,'create_by','createBy','varchar','String',0,0,'创建人',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063440,1496466324560822275,'update_by','updateBy','varchar','String',0,0,'更新人',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063441,1496466324544045057,'column_id','columnId','bigint','Long',1,0,'主键',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063442,1496466324544045057,'table_id','tableId','bigint','Long',0,0,'归属表id',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063443,1496466324544045057,'column_name','columnName','varchar','String',0,0,'列名称',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063444,1496466324544045057,'java_field','javaField','varchar','String',0,0,'JAVA字段名',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063445,1496466324544045057,'column_type','columnType','varchar','String',0,0,'列类型',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063446,1496466324544045057,'java_type','javaType','varchar','String',0,0,'JAVA类型',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063447,1496466324544045057,'if_pk','ifPk','tinyint','Integer',0,0,'是否主键: 0-不是，1-是',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063448,1496466324544045057,'if_increment','ifIncrement','tinyint','Integer',0,0,'是否自增: 0-不是，1-是',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063449,1496466324544045057,'column_comment','columnComment','varchar','String',0,0,'列描述',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063450,1496466324544045057,'sort','sort','int','Integer',0,0,'排序',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063451,1496466324544045057,'del_flag','delFlag','tinyint','Integer',0,0,'0-未删除，1-已删除',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063452,1496466324544045057,'create_time','createTime','datetime','LocalDateTime',0,0,'创建时间',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063453,1496466324544045057,'update_time','updateTime','datetime','LocalDateTime',0,0,'更新时间',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063454,1496466324544045057,'create_by','createBy','varchar','String',0,0,'创建者',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888'),(1496466324825063455,1496466324544045057,'update_by','updateBy','varchar','String',0,0,'更新者',0,0,'2022-02-23 20:45:50','2022-02-23 20:45:50','CS888888','CS888888');

