package com.cpms.framework.redis.utils;

import com.cpms.framework.common.utils.CsSpringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @description: redis工具类
 * @author: gulang
 * @time: 2021/8/5 17:07
 */
public class CsRedisUtil {
    @SuppressWarnings("rawtypes")
    private static final RedisTemplate REDISTEMPLATE =  CsSpringUtil.getBean(RedisTemplate.class);
    // =============================common============================
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param seconds 时间(秒)
     * @return
     */
    @SuppressWarnings("all")
    public static boolean expire(String key, long seconds) {
        return REDISTEMPLATE.expire(key, seconds, TimeUnit.SECONDS);
    }
    /**
     * 功能：设置过期(设置为<=0 key立即失效)
     */
    @SuppressWarnings("all")
    public static boolean expire(final String key, long expireTime, TimeUnit unit) {
        return REDISTEMPLATE.expire(key, expireTime, unit);
    }
    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    @SuppressWarnings("all")
    public static long getExpire(String key) {
        return REDISTEMPLATE.getExpire(key, TimeUnit.SECONDS);
    }
    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    @SuppressWarnings("all")
    public static boolean hasKey(String key) {
        return REDISTEMPLATE.hasKey(key);
    }
    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                REDISTEMPLATE.delete(key[0]);
            } else {
                REDISTEMPLATE.delete(CollectionUtils.arrayToList(key));
            }
        }
    }
    // ============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return (String)REDISTEMPLATE.opsForValue().get(key);
    }


    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    @SuppressWarnings("all")
    public static boolean set(String key, Object value) {
        REDISTEMPLATE.opsForValue().set(key, value);
        return true;
    }
    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param seconds 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    @SuppressWarnings("unchecked")
    public static void set(String key, Object value, long seconds) {
        REDISTEMPLATE.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param expireTime 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     */
    @SuppressWarnings("unchecked")
    public static boolean set(String key, Object value, long expireTime,TimeUnit unit) {
        REDISTEMPLATE.opsForValue().set(key, value, expireTime, unit);
        return true;
    }

    /**
     * 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return
     */
    @SuppressWarnings("all")
    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return REDISTEMPLATE.opsForValue().increment(key, delta);
    }
    /**
     * 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return
     */
    @SuppressWarnings("all")
    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return REDISTEMPLATE.opsForValue().increment(key, -delta);
    }
    // ================================Map=================================
    /**
     * HashGet
     * @param key 键 不能为null
     * @param field 项 不能为null
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public static Object hget(String key, String field) {
        return REDISTEMPLATE.opsForHash().get(key, field);
    }
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    @SuppressWarnings("unchecked")
    public static Map<Object, Object> hmget(String key) {
        return REDISTEMPLATE.opsForHash().entries(key);
    }
    /**
     * 存放一张Hash表
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    @SuppressWarnings("unchecked")
    public static boolean hmset(String key, Map<String, Object> map) {
        REDISTEMPLATE.opsForHash().putAll(key, map);
        return true;
    }
    /**
     * 存放一张Hash表 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    @SuppressWarnings("unchecked")
    public static boolean hmset(String key, Map<String, Object> map, long time) {
        REDISTEMPLATE.opsForHash().putAll(key, map);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param field 项
     * @param value 值
     * @return true 成功 false失败
     */
    @SuppressWarnings("unchecked")
    public static boolean hset(String key, String field, Object value) {
        REDISTEMPLATE.opsForHash().put(key, field, value);
        return true;
    }
    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param field 项
     * @param value 值
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    @SuppressWarnings("unchecked")
    public static boolean hset(String key, String field, Object value, long time) {
        REDISTEMPLATE.opsForHash().put(key, field, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }
    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param field 项 可以使多个 不能为null
     */
    @SuppressWarnings("unchecked")
    public static void hdel(String key, Object... field) {
        REDISTEMPLATE.opsForHash().delete(key, field);
    }
    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param field 项 不能为null
     * @return true 存在 false不存在
     */
    @SuppressWarnings("unchecked")
    public static boolean hHasKey(String key, String field) {
        return REDISTEMPLATE.opsForHash().hasKey(key, field);
    }
    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param field 项
     * @param by 要增加几(大于0)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static double hincr(String key, String field, double by) {
        return REDISTEMPLATE.opsForHash().increment(key, field, by);
    }
    /**
     * hash递减
     * @param key 键
     * @param field 项
     * @param by 要减少记(小于0)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static double hdecr(String key, String field, double by) {
        return REDISTEMPLATE.opsForHash().increment(key, field, -by);
    }
    // ============================set=============================
    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Set<Object> sGet(String key) {
        return REDISTEMPLATE.opsForSet().members(key);
    }
    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    @SuppressWarnings("all")
    public static boolean sHasKey(String key, Object value) {
        return REDISTEMPLATE.opsForSet().isMember(key, value);
    }
    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    @SuppressWarnings("all")
    public static Long sSet(String key, Object... values) {
        return REDISTEMPLATE.opsForSet().add(key, values);
    }
    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    @SuppressWarnings("unchecked")
    public static Long sSetAndTime(String key, long time, Object... values) {
        Long count = REDISTEMPLATE.opsForSet().add(key, values);
        if (time > 0) {
            expire(key, time);
        }
        return count;
    }
    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    @SuppressWarnings("all")
    public static Long setSize(String key) {
        return REDISTEMPLATE.opsForSet().size(key);
    }
    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    @SuppressWarnings("all")
    public static Long setRemove(String key, Object... values) {
        return REDISTEMPLATE.opsForSet().remove(key, values);
    }
    // ===============================list=================================
    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Object> lGet(String key, long start, long end) {
        return REDISTEMPLATE.opsForList().range(key, start, end);
    }
    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    @SuppressWarnings("all")
    public static Long lGetListSize(String key) {
        return REDISTEMPLATE.opsForList().size(key);
    }
    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object lGetIndex(String key, long index) {
        return REDISTEMPLATE.opsForList().index(key, index);
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean lSet(String key, Object value) {
        REDISTEMPLATE.opsForList().rightPush(key, value);
        return true;
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean lSet(String key, Object value, long time) {
        REDISTEMPLATE.opsForList().rightPush(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }
    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean lSet(String key, List<Object> value) {
        REDISTEMPLATE.opsForList().rightPushAll(key, value);
        return true;
    }
    /**
     * 将list放入缓存
     *
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean lSet(String key, List<Object> value, long time) {
        REDISTEMPLATE.opsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }
    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean lUpdateIndex(String key, long index, Object value) {
        REDISTEMPLATE.opsForList().set(key, index, value);
        return true;
    }
    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    @SuppressWarnings("unchecked")
    public static Long lRemove(String key, long count, Object value) {
        return REDISTEMPLATE.opsForList().remove(key, count, value);
    }
}
