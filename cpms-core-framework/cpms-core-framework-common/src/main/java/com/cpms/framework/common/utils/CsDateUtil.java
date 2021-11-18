package com.cpms.framework.common.utils;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.Objects;

/**
 * @description: 日期工具类
 * @author: gulang
 * @time: 2021/5/20 16:59
 */
public class CsDateUtil extends DateFormatUtils {
    public static final String TIME_STAMP_S = "s";
    public static final String TIME_STAMP_MS = "ms";
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String HHMMSSSSS = "HHmmssSSS";
    public static final String HHMMSS = "HH:mm:ss";

    public static String dateFormat(Date date){
       return FastDateFormat.getInstance(DEFAULT_PATTERN).format(date);
    }

    public static String dateFormat(Date date,String pattern){
        return FastDateFormat.getInstance(pattern).format(date);
    }

    public static int getCurrentMonth(){
        return getLocalDate().get(ChronoField.MONTH_OF_YEAR);
    }

    public static int getCurrentYear(){
        return getLocalDate().getYear();
    }
    public static int getCurrentDay(){
        return getLocalDate().getDayOfMonth();
    }
    public static int getCurrentWeek(){
        return getLocalDate().get(ChronoField.DAY_OF_WEEK);
    }
    public static LocalDate getLocalDate(){
        return LocalDate.now();
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
