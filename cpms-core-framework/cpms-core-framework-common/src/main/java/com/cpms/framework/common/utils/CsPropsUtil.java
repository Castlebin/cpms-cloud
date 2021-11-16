package com.cpms.framework.common.utils;
import org.springframework.core.env.Environment;

import java.util.Objects;


/**
 * @description: 在非spring环境中获取配置文件属性工具类
 * @author: gulang
 * @time: 2021/7/7 15:16
 */
public class CsPropsUtil {
    // 凡是能通过@Value注解获取的配置属性，都能被Environment获取
    private static final Environment ENVIRONMENT =  CsSpringUtil.getBean(Environment.class);

    /**
     *  通过key获取配置文件属性值
     * @param propertyKey 配置文件KEY
     * @return
     */
    public static Object getPropertyVal(String propertyKey){
        return ENVIRONMENT.getProperty(propertyKey);
    }

    public static Object getObject(String propertyKey){
        return getPropertyVal(propertyKey);
    }

    public static boolean getBoolean(String propertyKey){
        Object propertyVal = getPropertyVal(propertyKey);
        if(Objects.isNull(propertyVal)) {
            return  false;
        }
        return Boolean.parseBoolean(propertyVal.toString());
    }

    public static String getString(String propertyKey){
        Object propertyVal = getPropertyVal(propertyKey);
        if(Objects.isNull(propertyVal)) {
            return  "";
        }
        return  String.valueOf(propertyVal);
    }
}
