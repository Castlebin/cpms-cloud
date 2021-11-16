package com.cpms.framework.common.utils;

/**
 * @description: 生成业务ID
 * @author: gulang
 * @time: 2021/9/28 11:31
 */
public class CsGenerateIdUtil {
    /**
     * 生成所属租户的用户账号
     * @param accountPrefix  账号前缀
     * @param len  除去前缀的账号长度
     * @param seed 起始值
     * @return
     */
    public static String userAccount(String accountPrefix,int len,int seed){
        if(CsStringUtil.isBlank(accountPrefix)) {
            accountPrefix = "CS";
        }
       return accountPrefix +String.format("%0"+len+"d", seed);
    }

//    public static void main(String[] args) {
//        System.out.println(userAccount("CS",6,1));
//    }
}
