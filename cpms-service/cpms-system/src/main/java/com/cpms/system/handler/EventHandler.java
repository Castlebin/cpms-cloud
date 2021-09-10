package com.cpms.system.handler;

import com.alibaba.fastjson.JSON;
import com.cpms.framework.log.dto.LogDTO;
import com.cpms.framework.log.event.LogEvent;
import com.cpms.log.api.modules.log.dto.HandlerLogDTO;
import com.cpms.log.api.modules.log.feign.ILogClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @description: 事件监听处理类
 * @author: gulang
 * @time: 2021/8/18 17:39
 */
@Slf4j
@Component
@EnableAsync
public class EventHandler {
    @Resource
    private ILogClient logClient;
    /**
     * 一般监听的事件常常采用异步执行方式,必须加上@Async注解，入口类必须要加 @EnableAsync 注解开启异步处理
     *  defaultAsyncTaskPool 指定线程池
     * @param event
     */
    @Async("defaultAsyncTaskPool")
    @EventListener
    public void onHandlerLogEvent(LogEvent event) {
        log.info("[onHandlerLogEvent] 监听操作日志事件....LogEvent={},source={}", JSON.toJSONString(event),JSON.toJSONString(event.getSource()));
        LogDTO eventData = event.getEventData();
        HandlerLogDTO handlerLogDTO = new HandlerLogDTO();
        BeanUtils.copyProperties(eventData,handlerLogDTO);
        logClient.recordHandlerLog(handlerLogDTO);
    }
}
