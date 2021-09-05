package com.cpms.framework.log.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 操作日志注解，作用在方法上
 * @author: gulang
 * @time: 2021/8/16 17:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperLog {
    /**
     * 描述
     * @return
     */
    String desc()  default "";

}
