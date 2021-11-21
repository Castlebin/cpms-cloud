package com.cpms.framework.secure.annotations;

import java.lang.annotation.*;

/**
 * @description: 自定义授权注解
 * @author: gulang
 * @time: 2021/8/3 15:14
 */
// 作用再方法或者类上
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuth {
    /**
     *  多个权限，使用英文逗号分割，只要满足其中一个则表示拥有权限
     * @return
     */
    String value();
}
