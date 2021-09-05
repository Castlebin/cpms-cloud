package com.cpms.framework.secure.aspect;

import com.cpms.framework.common.core.secure.TokenUserInfo;
import com.cpms.framework.common.enums.GlobalResponseResultEnum;
import com.cpms.framework.common.exception.BizException;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.framework.redis.utils.CsRedisUtil;
import com.cpms.framework.secure.annotations.PreAuth;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @description: 授权注解拦截切面
 * @author: gulang
 * @time: 2021/8/3 15:50
 */
@Aspect
@Configuration
public class AuthAspect {
    private static final String CACHE_LOGIN_USER_INFO_KEY = "login:user:info:";
    private static final String PERMISSION_KEY = "permission";
    /**
     *  execution表达式
      * @Pointcut(value = "execution(* com.cpms..controller..*.*(..)) || execution(* com.cpms..feign..*.*(..))")
      * execution（）	表达式的主体；
      * 第一个”*“符号：表示返回值的类型任意；
      * ”..“：表示当前包及子孙包
      * ”..*“：表示当前包及子孙包所有的类
      * .*(..)：表示类中任何方法名，括号表示参数，两个点表示任何参数类型
      *
      *  !Note:服务内部通过feign远程调用接口时也会被拦截
     */

    /**
     *  定义切点
     * （1）@annotation：用来拦截所有被某个注解修饰的方法
     * （2）@within：用来拦截所有被某个注解修饰的类
     */
    @Pointcut("@annotation(com.cpms.framework.secure.annotations.PreAuth)"
            + "|| @within(com.cpms.framework.secure.annotations.PreAuth)")
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
        // 方法注解可以覆盖类型注解-方法注解优先级大于类注解
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
        TokenUserInfo tokenInfo = CsSecureUtil.getUser();
        if(CsSecureUtil.isSuperAdmin()) {
            return  true;
        }
        String cachePermissions = (String) CsRedisUtil.hget(CACHE_LOGIN_USER_INFO_KEY + tokenInfo.getUserId(), PERMISSION_KEY);
        if(StringUtils.isBlank(cachePermissions)) {
            return false;
        }
        return Arrays.asList(cachePermissions.split(",")).contains(permission);
    }
}
