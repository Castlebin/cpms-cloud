package com.cpms.framework.redis.utils;

import com.cpms.common.utils.CsSpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @description: redisson分布式锁工具类
 * @author: gulang
 * @time: 2021/8/24 14:00
 */
@Slf4j
public class CsRedissonUtil {
    /**
     * 获取分布式锁bean
     */
    private static final RedissonClient REDISSONCLIENT = CsSpringUtil.getBean(RedissonClient.class);
    /**
     * 功能描述: 获取锁对象
     *
     * @param lockKey 锁key
     * @return org.redisson.api.RLock 返回redssion的锁
     */
    public static RLock getLock(String lockKey) {
        return REDISSONCLIENT.getLock(lockKey);
    }

    /**
     * 功能描述: 锁key，默认30s
     * ！NOTE:建议使用该方法设置锁，该方法可以实现锁续期功能
     * @param lockKey 锁key
     */
    public static void lock(String lockKey) {
        RLock lock = getLock(lockKey);
        lock.lock();
    }

    /**
     * 功能描述: 锁指定时间key(单位:秒)
     *
     * @param lockKey 锁的key
     * @param timeout 过期时间
     */
    public static void lock(String lockKey, long timeout) {
        RLock lock = getLock(lockKey);
        lock.lock(timeout, TimeUnit.SECONDS);
    }

    /**
     * 功能描述: 锁指定时间key(单位可以自定义)
     *
     * @param lockKey 锁的key
     * @param unit    过期时间单位
     * @param timeout 过期时间
     */
    public static void lock(String lockKey, TimeUnit unit, long timeout) {
        RLock lock = getLock(lockKey);
        lock.lock(timeout, unit);
    }


    /**
     * 功能描述: 尝试加锁最多等待waitTime秒，上锁以后leaseTime秒自动解锁
     *
     * @param lockKey   锁的key
     * @param unit      过期时间单位
     * @param waitTime  等待时间
     * @param leaseTime 自动解锁时间
     * @return boolean  是否加锁成功
     */
    public static boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
        RLock lock = getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            log.error("tryLock exception lockKey:{},waitTime:{}, leaseTime:{},errMsg:{}",
                    lockKey, waitTime, leaseTime, e.getMessage(), e);
            return false;
        }
    }


     /**
     * 功能描述: 尝试加锁最多等待waitTime秒
     *
     * @param lockKey  锁的key
     * @param unit     过期时间单位
     * @param waitTime 等待时间
     * @return boolean 是否加锁成功
     */
    public static boolean tryLock(String lockKey, TimeUnit unit, long waitTime) {
        RLock lock = getLock(lockKey);
        try {
            return lock.tryLock(waitTime, unit);
        } catch (InterruptedException e) {
            log.error("tryLock exception lockKey:{},waitTime:{},errMsg:{}",
                    lockKey, waitTime, e.getMessage(), e);
            return false;
        }
    }


    /**
     * 功能描述:  解锁key
     *
     * @param lockKey 锁key
     */
    public static void unlock(String lockKey) {
        RLock lock = getLock(lockKey);
        // 只释放当前线程自己的锁，不能释放别的线程锁
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 功能描述:  解锁key
     *
     * @param lock 锁对象
     */
    public static void unlock(RLock lock) {
        // 只释放当前线程自己的锁，不能释放别的线程锁
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * @param lockKey 锁key
     * @return boolean true:是 false:否
     */
    public static boolean isLocked(String lockKey) {
        RLock lock = getLock(lockKey);
        return lock.isLocked();
    }

}
