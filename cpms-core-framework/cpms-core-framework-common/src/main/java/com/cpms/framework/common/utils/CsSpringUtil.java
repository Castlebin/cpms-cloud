package com.cpms.framework.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * @description: spring 上下文工具类
 * @author: gulang
 * @time: 2021/6/7 19:52
 */
@Slf4j
public class CsSpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    @SuppressWarnings("all")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(CsSpringUtil.applicationContext == null) {
            CsSpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * @Description: 获取spring容器中的bean,通过bean名称获取
     * @param beanName bean名称
     * @return: Object 返回Object,需要做强制类型转换
     */
    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }

    /**
     * @Description: 获取spring容器中的bean, 通过bean类型获取
     * @param beanClass bean 类型
     * @return: T 返回指定类型的bean实例
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }

    /**
     * @Description: 获取spring容器中的bean, 通过bean名称和bean类型精确获取
     * @param beanName bean 名称
     * @param beanClass bean 类型
     * @return: T 返回指定类型的bean实例
     */
    public static <T> T getBean(String beanName, Class<T> beanClass){
        return applicationContext.getBean(beanName,beanClass);
    }

    /**
     *  spring事件发布方法
     * @param event
     */
    public static void publishEvent(ApplicationEvent event) {
        if (applicationContext != null) {
            try {
                applicationContext.publishEvent(event);
            } catch (Exception ex) {
                log.error("[publishEvent]，spring事件发布异常,event={}", JSON.toJSONString(event),ex);
            }
        }
    }
}
