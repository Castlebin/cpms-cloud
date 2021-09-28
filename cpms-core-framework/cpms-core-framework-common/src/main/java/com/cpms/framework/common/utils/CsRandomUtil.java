package com.cpms.framework.common.utils;

import com.cpms.framework.common.enums.RandomTypeEnum;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @description:
 * @author: gulang
 * @time: 2021/9/28 10:05
 */
public class CsRandomUtil {
    private static final String S_INT = "0123456789";
    private static final String S_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String S_ALL = S_INT + S_STR;

    /**
     * 随机数生成
     * @param count      字符长度
     * @param randomType 随机数类别
     * @return 随机数
     */
    public static String random(int count, RandomTypeEnum randomType) {
        if (count == 0) {
            return "";
        }
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            if (RandomTypeEnum.INT == randomType) {
                buffer[i] = S_INT.charAt(random.nextInt(S_INT.length()));
            } else if (RandomTypeEnum.STRING == randomType) {
                buffer[i] = S_STR.charAt(random.nextInt(S_STR.length()));
            } else {
                buffer[i] = S_ALL.charAt(random.nextInt(S_ALL.length()));
            }
        }
        return new String(buffer);
    }

//    public static void main(String[] args) {
//        System.out.println(random(6,RandomTypeEnum.INT));
//    }

}
