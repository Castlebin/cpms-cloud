package com.cpms.common.constant;

/**
 * @description: 公共常量
 * @author: gulang
 * @time: 2021/9/23 17:14
 */
public class CommonConstant {

    /** 租户管理员默认角色名称 **/
    public static final String TENANT_ADMINISTRATOR_ROLE_NAME = "管理员";

    /** 租户管理员默认角色编码 **/
    public static final String TENANT_ADMINISTRATOR_ROLE_CODE = "TENANT_ADMINISTRATOR";

    /** 租户管理员默认角色描述 **/
    public static final String TENANT_ADMINISTRATOR_ROLE_DESC = "租户管理员角色";

    /** 数据状态:已禁用 **/
    public static final int DATA_STATUS_FORBIDDEN = 1;
    /** 数据状态:正常 **/
    public static final  int DATA_STATUS_NORMAL = 0;

    /** 数据状态:已删除 **/
    public static final int DEL_FLAG_DELETED = 1;
    /** 数据状态:未删除 **/
    public static final int DEL_FLAG_NOT_DELETED = 0;

    /** 类型：菜单 **/
    public static final int TYPE_MENU = 0;
    /** 类型：按钮 **/
    public static final int TYPE_BUTTON = 1;

    /** 系统数据 **/
    public static final int SYS_DATA = 1;
}
