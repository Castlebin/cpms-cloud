package com.cpms.system.handler;

import com.alibaba.fastjson.JSON;
import com.cpms.framework.log.dto.LogDTO;
import com.cpms.framework.log.event.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description: 事件监听处理类
 * @author: gulang
 * @time: 2021/8/18 17:39
 */
@Component
@Slf4j
public class EventHandler {
    /**
     * 一般监听的事件常常采用异步执行方式 但是入口类必须要加 @EnableAsync 注解开启异步处理
     * @param event
     */
    @Async
    @EventListener
    public void onHandlerLogEvent(LogEvent event) {
        log.info("[onHandlerLogEvent] 监听操作日志事件....LogEvent={}", JSON.toJSONString(event));
        Object source = event.getSource();
        LogDTO eventData = event.getEventData();
    }
}
