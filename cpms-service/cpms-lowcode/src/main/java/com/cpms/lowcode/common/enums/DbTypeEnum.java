package com.cpms.lowcode.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/27 15:37
 */
@Getter
@AllArgsConstructor
public enum DbTypeEnum {
    MYSQL("1","com.mysql.cj.jdbc.Driver","jdbc:mysql://${dbIpPort}/${dbName}?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai"),

    ;
    private final String type;

    private final String driverClass;

    private final String url;


    public static DbTypeEnum getByType(String type){
        for (DbTypeEnum value : values()) {
            if(value.type.equals(type)){
                return  value;
            }
        }
        return null;
    }


}
