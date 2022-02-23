package com.cpms.lowcode.common.aspect;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.cpms.lowcode.config.DataSourceCreatorConfig;
import com.cpms.lowcode.modules.dynamic.bo.DynamicDbInfoBO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/28 14:40
 */
@Aspect
@Component
public class DynamicDbAspect {
    @Resource
    private DataSourceCreatorConfig dataSourceCreatorConfig;

    @Pointcut("execution(public * com.cpms.lowcode.modules.dynamic.manage.DynamicDbManage.*(..))")
    public void dynamicDbAspectPoint() {
    }


    @Around("dynamicDbAspectPoint()")
    public Object inject(ProceedingJoinPoint pjp)throws Exception {
        // 从切点获取方法的参数
        Object[] args = pjp.getArgs();
        DynamicDbInfoBO dynamicDBInfo = (DynamicDbInfoBO)args[0];
        // 初始化DataSource
        dataSourceCreatorConfig.initDynamicDataSource(dynamicDBInfo);
        // 上下文存储DataSource对应的key
        DynamicDataSourceContextHolder.push(dynamicDBInfo.getDsKey());
        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        // 使用完清理相应的key
        DynamicDataSourceContextHolder.clear();
        return obj;
    }

    @AfterThrowing("dynamicDbAspectPoint()")
    public void failHandle(JoinPoint joinPoint) {
        // 从切点获取方法的参数清理threadLocalKey
        DynamicDataSourceContextHolder.clear();
    }
}
