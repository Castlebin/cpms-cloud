package com.cpms.framework.common.utils.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/1 16:10
 */
public class ThreadPoolCreate {
    private final ThreadPoolProperties poolProperties;
    private static final int QUEUE_CAPACITY = 65535;
    private static final int WAIT_SECONDS = 60;
    private static final boolean COMPLETE_ON_SHUTDOWN = true;
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final String THREAD_NAME_PREFIX = "thread-pool-builder-";

    public ThreadPoolCreate(){
        int num = Runtime.getRuntime().availableProcessors();
        poolProperties = new ThreadPoolProperties();
        poolProperties.setMaxPoolSize(num*2);
        poolProperties.setCorePoolSize(num*4);
        poolProperties.setQueueCapacity(QUEUE_CAPACITY);
        poolProperties.setWaitForTasksToCompleteOnShutdown(COMPLETE_ON_SHUTDOWN);
        poolProperties.setAwaitTerminationSeconds(WAIT_SECONDS);
        poolProperties.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        poolProperties.setThreadNamePrefix(THREAD_NAME_PREFIX);
    }

    public ThreadPoolCreate(ThreadPoolProperties poolProperties) {
        Assert.notNull(poolProperties, "threadPoolProperties 不能为空");
        Assert.notNull(poolProperties.getCorePoolSize(), "CorePoolSize 不能为空");
        Assert.notNull(poolProperties.getMaxPoolSize(), "MaxPoolSize 不能为空");
        Assert.notNull(poolProperties.getQueueCapacity(), "QueueCapacity 不能为空");
        Assert.notNull(poolProperties.getAwaitTerminationSeconds(), "AwaitTerminationSeconds 不能为空");
        Assert.notNull(poolProperties.getKeepAliveSeconds(), "keepAliveSeconds 不能为空");
        Assert.notNull(poolProperties.getWaitForTasksToCompleteOnShutdown(), "WaitForTasksToCompleteOnShutdown 不能为空");
        this.poolProperties = poolProperties;
    }

    /**
     * 获取固定线程的线程池
     * @param nThreads
     * @return
     */
    public ThreadPoolTaskExecutor buildThreadPoolExecutor(Integer nThreads) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        if (Objects.isNull(nThreads)) {
            // 设置核心线程数
            executor.setCorePoolSize(poolProperties.getCorePoolSize());
            // 设置最大线程数
            executor.setMaxPoolSize(poolProperties.getMaxPoolSize());
        } else {
            // 设置核心线程数
            executor.setCorePoolSize(nThreads);
            // 设置最大线程数
            executor.setMaxPoolSize(nThreads);
        }
        // 设置队列容量
        executor.setQueueCapacity(poolProperties.getQueueCapacity());
        // 这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(poolProperties.getWaitForTasksToCompleteOnShutdown());
        // 当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(poolProperties.getKeepAliveSeconds());
        // 等待指定时间没关闭自动关闭
        executor.setAwaitTerminationSeconds(poolProperties.getAwaitTerminationSeconds());
        // 线程名称前缀
        executor.setThreadNamePrefix(poolProperties.getThreadNamePrefix() == null ?
                THREAD_NAME_PREFIX : poolProperties.getThreadNamePrefix());
        executor.initialize();
        return  executor;
    }

    public static class ThreadPoolProperties {
        /**
         * 核心线程数 默认处理器数 * 2
         */
        private Integer corePoolSize;
        /**
         * 线程池线程总数， 默认处理器数 * 4
         */
        private Integer maxPoolSize;
        /**
         * 阻塞队列容量 默认65535
         */
        private Integer queueCapacity;

        /**
         * 当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
         */
        private Integer keepAliveSeconds;
        /**
         * 默认 true 用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bea
         */
        private Boolean waitForTasksToCompleteOnShutdown;
        /**
         * 默认 60s 该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
         */
        private Integer awaitTerminationSeconds;
        /**
         * 线程名称前缀
         */
        private String threadNamePrefix;

        public Integer getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(Integer corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public Integer getMaxPoolSize() {
            return maxPoolSize;
        }

        public void setMaxPoolSize(Integer maxPoolSize) {
            this.maxPoolSize = maxPoolSize;
        }

        public Integer getQueueCapacity() {
            return queueCapacity;
        }

        public void setQueueCapacity(Integer queueCapacity) {
            this.queueCapacity = queueCapacity;
        }

        public Boolean getWaitForTasksToCompleteOnShutdown() {
            return waitForTasksToCompleteOnShutdown;
        }

        public void setWaitForTasksToCompleteOnShutdown(Boolean waitForTasksToCompleteOnShutdown) {
            this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
        }

        public Integer getAwaitTerminationSeconds() {
            return awaitTerminationSeconds;
        }

        public void setAwaitTerminationSeconds(Integer awaitTerminationSeconds) {
            this.awaitTerminationSeconds = awaitTerminationSeconds;
        }

        public Integer getKeepAliveSeconds() {
            return keepAliveSeconds;
        }

        public void setKeepAliveSeconds(Integer keepAliveSeconds) {
            this.keepAliveSeconds = keepAliveSeconds;
        }

        public String getThreadNamePrefix() {
            return threadNamePrefix;
        }

        public void setThreadNamePrefix(String threadNamePrefix) {
            this.threadNamePrefix = threadNamePrefix;
        }
    }
}
