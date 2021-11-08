package com.cpms.framework.common.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 集合工具类
 * @author: gulang
 * @time: 2021/9/15 11:28
 */
public class CsBeanUtil extends BeanUtils {

    /**
     * @param sources 数据源类
     * @param target  目标类
     * @param <S>     数据源泛型
     * @param <T>     目标类泛型
     * @return 返回目标bean集合
     */
    public static <S, T> List<T> copyList(List<S> sources, Class<T> target) {
        List<T> list = new ArrayList<>();
        if (CsCollectionUtil.isEmpty(sources)) {
            return null;
        }
        for (S source : sources) {
            T t = null;
            try {
                t = target.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.notNull(t, "目标类不能为空");
            CsBeanUtil.copyProperties(source, t);
            list.add(t);
        }
        return list;
    }
}
