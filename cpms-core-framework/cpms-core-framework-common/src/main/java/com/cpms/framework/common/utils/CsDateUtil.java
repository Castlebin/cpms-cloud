package com.cpms.framework.common.utils;


import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;
import java.util.Objects;

/**
 * @description: 日期工具类
 * @author: gulang
 * @time: 2021/5/20 16:59
 */
public class CsDateUtil {
    public static final String TIME_STAMP_S = "s";
    public static final String TIME_STAMP_MS = "ms";
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String dateFormat(Date date){
       return FastDateFormat.getInstance(DEFAULT_PATTERN).format(date);
    }

    /**
     * 获取当前时间戳
     * @param type s:秒  ms:毫秒
     * @return
     */
    public static long currentTimeStamp(String type){
        long timeStamp = System.currentTimeMillis();
        if(Objects.equals(type,TIME_STAMP_S)) {
            return timeStamp/1000;
        }
        if(Objects.equals(type,TIME_STAMP_MS)) {
            return timeStamp;
        }
        return 0L;
    }
}
