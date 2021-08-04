package com.cpms.framework.security.aop;

import com.cpms.common.core.secure.TokenUserInfo;
import com.cpms.common.enums.GlobalResponseResultEnum;
import com.cpms.common.exception.BizException;
import com.cpms.common.utils.SecureUtil;
import com.cpms.framework.security.annotations.PreAuth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description: 授权注解拦截切面
 * @author: gulang
 * @time: 2021/8/3 15:50
 */
@Aspect
@Configuration
@Slf4j
public class AuthAspect {
    /**
     *  定义切点
     * （1）@annotation：用来拦截所有被某个注解修饰的方法
     * （2）@within：用来拦截所有被某个注解修饰的类
     */
    @Pointcut("@annotation(com.cpms.framework.security.annotations.PreAuth)"
            + "|| @within(com.cpms.framework.security.annotations.PreAuth)")
    public void authAspectPoint(){}

    @Before("authAspectPoint()")
    public void handelBefore(JoinPoint point) throws NoSuchMethodException {
        Class<?> target1 = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        resolveAuth(target1, signature.getMethod());
    }


    /**
     * 提取目标对象方法注解和类型注解中的权限数据
     *
     * @param clazz
     * @param method
     */
    private void resolveAuth(Class<?> clazz, Method method) throws NoSuchMethodException {
        PreAuth auth= null;
        Class<?>[] types = method.getParameterTypes();
        // 默认使用类型注解-也就是使用类上面的注解
        if (clazz.isAnnotationPresent(PreAuth.class)) {
            auth = clazz.getAnnotation(PreAuth.class);
        }
        // 方法注解可以覆盖类型注解-也就是使用方法上的注解去替换类上面的注解
        Method m = clazz.getMethod(method.getName(), types);
        if (m.isAnnotationPresent(PreAuth.class)) {
            auth = m.getAnnotation(PreAuth.class);
        }

        if(auth == null) {
            throw new BizException(GlobalResponseResultEnum.INTERNAL_SERVER_EXCEPTION_ERROR);
        }
        if(!hasPermission(auth.value())) {
            throw new BizException(GlobalResponseResultEnum.REQ_UNAUTHORIZED_ERROR);
        }
    }

    /**
     *  判断是否有权限
     * @param permission
     * @return
     */
    public boolean hasPermission(String permission){
        if(StringUtils.isBlank(permission)) {
            return false;
        }
        TokenUserInfo tokenInfo = SecureUtil.getUser();
        return tokenInfo.getPermissions().stream().anyMatch(permission::equals);
    }

}
