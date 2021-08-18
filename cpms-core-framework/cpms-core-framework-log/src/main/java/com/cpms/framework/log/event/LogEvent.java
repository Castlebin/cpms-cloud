package com.cpms.framework.log.event;

import com.cpms.common.core.events.BaseEvent;
import com.cpms.framework.log.dto.LogDTO;

/**
 * @description: 日志事件
 * @author: gulang
 * @time: 2021/8/18 17:27
 */
public class LogEvent extends BaseEvent<LogDTO> {
    public LogEvent(Object source, LogDTO eventData) {
        super(source, eventData);
    }

    public LogEvent(LogDTO eventData) {
        super(eventData);
    }
}
