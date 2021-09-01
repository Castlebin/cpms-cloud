package com.cpms.common.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @description: 线程池构建者
 * @author: gulang
 * @time: 2021/9/1 16:08
 */
public class ThreadPoolBuilder {

    /**
     * 创建线程池
     */
    public static ThreadPoolTaskExecutor buildThreadPool(Integer nThreads) {
        return new ThreadPoolCreate().buildThreadPoolExecutor(nThreads);
    }

    /**
     * 自定义属性创建线程池
     * @param properties
     * @param nThreads
     * @return
     */
    public static ThreadPoolTaskExecutor buildCustomThreadPool(ThreadPoolCreate.ThreadPoolProperties properties,
                                                            Integer nThreads) {
        return new ThreadPoolCreate(properties).buildThreadPoolExecutor(nThreads);
    }
}
