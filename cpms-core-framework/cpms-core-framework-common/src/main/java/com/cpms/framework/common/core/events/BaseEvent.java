package com.cpms.framework.common.core.events;


import org.springframework.context.ApplicationEvent;

/**
 * @description: 自定义事件基类
 * @author: gulang
 * @time: 2021/8/18 16:52
 */
public abstract class BaseEvent<T> extends ApplicationEvent {
    private static final long serialVersionUID = 895628808370649881L;

    protected T eventData;

    /**
     * @param source  多处发布相同的事件，建议发布事件时将this作为source传递，便于通过分析日志确定发布源。
     * @param eventData  事件发布数据
     */
    public BaseEvent(Object source, T eventData){
        super(source);
        this.eventData = eventData;
    }

    public BaseEvent(T eventData){
        super(eventData);
    }

    public T getEventData() {
        return eventData;
    }
    public void setEventData(T eventData) {
        this.eventData = eventData;
    }
}
