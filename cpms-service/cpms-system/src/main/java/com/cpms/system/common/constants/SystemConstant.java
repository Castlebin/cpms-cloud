package com.cpms.system.common.constants;

/**
 * @description: 系统常量配置
 * @author: gulang
 * @time: 2021/7/8 10:51
 */
public class SystemConstant {
   /** 自定义配置文件名,使用英文逗号分隔 **/
   public static final String CUSTOM_PROPS_FILE_NAME = "system.yml";
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
}
