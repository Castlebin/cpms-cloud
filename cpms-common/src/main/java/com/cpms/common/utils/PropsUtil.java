package com.cpms.common.utils;
import org.springframework.core.env.Environment;


/**
 * @description: 在非spring环境中获取配置文件属性工具类
 * @author: gulang
 * @time: 2021/7/7 15:16
 */
public class PropsUtil {
    // 凡是能通过@Value注解获取的配置属性，都能被Environment获取
    private static final Environment ENVIRONMENT =  SpringUtil.getBean(Environment.class);

    /**
     *  通过key获取配置文件属性
     * @param propertyKey 配置文件KEY
     * @return
     */
    public static Object getProperty(String propertyKey){
        return ENVIRONMENT.getProperty(propertyKey);
    }
}
