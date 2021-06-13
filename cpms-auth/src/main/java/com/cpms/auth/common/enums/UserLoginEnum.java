package com.cpms.auth.common.enums;

/**
 * @description:
 * @author: gulang
 * @time: 2021/6/7 10:49
 */
public enum UserLoginEnum {
    /**
     * 后台管理系统登录
     */
    SYS_ADMIN("sys-admin", 1),

    /**
     * 微信小程序、公众号登录
     */
    WX_MINI("wx-mini", 2),

    /**
     * app 登录
     */
    APP("app", 3),
    ;



    final String name;
    final int  category;
    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    UserLoginEnum(String name, int category) {
        this.name = name;
        this.category = category;
    }
}
