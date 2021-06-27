package com.cpms.common.constant;

/**
 * @description: 系统常量
 * @author: gulang
 * @time: 2021/5/20 17:01
 */
public interface AppConstant {
    String BASE_PACKAGES = "com.cpms";
    String APPLICATION_NAME = "Cpms";
    /**
     * 应用名前缀
     */
    String APPLICATION_NAME_PREFIX = "cpms-server-";

    /**
     * 授权服务
     */
    String APPLICATION_AUTH_NAME = APPLICATION_NAME_PREFIX+"auth";
    String AUTH_API_PREFIX = "/authMicroservice";

    /**
     * 网关服务
     */
    String APPLICATION_GATEWAY_NAME = APPLICATION_NAME_PREFIX+"gateway";
    String GATEWAY_API_PREFIX = "/gatewayService";
    /**
     * 后台日志服务
     */
    String APPLICATION_LOG_NAME = APPLICATION_NAME_PREFIX+"log";
    String LOG_API_PREFIX = "/logMicroservice";
    /**
     * 管理后台服务
     */
    String APPLICATION_SYSTEM_NAME = APPLICATION_NAME_PREFIX+"system";
    String SYSTEM_API_PREFIX = "/systemMicroservice";

}
