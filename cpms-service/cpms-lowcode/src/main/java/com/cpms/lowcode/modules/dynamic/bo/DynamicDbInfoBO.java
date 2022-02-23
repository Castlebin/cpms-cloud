package com.cpms.lowcode.modules.dynamic.bo;

import com.cpms.framework.common.utils.CsStringUtil;
import com.cpms.lowcode.common.enums.DbTypeEnum;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;
import java.util.Map;

/**
 * @author gulang
 * @Description:
 * @time: 2022/1/26 19:48
 */
@Data
@Builder
public class DynamicDbInfoBO implements Serializable {
    private static final long serialVersionUID = 935288243233531322L;

    /**
     * 数据库url
     */
    private String dbUrl;
    /**
     * host ip
     */
    private String host;
    /**
     * 数据库端口
     */
    private Integer port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据库名
     */
    private String dbName;
    /**
     * 驱动名
     */
    private String driverClass;
    /**
     * 动态dskey
     */
    private String dsKey;

    private String tableName;

    /**
     * 解析dburl构造属性
     */
    public DynamicDbInfoBO initDBInfo(String dbIpPort,String dbName, String username, String password,String driverType) {
        Map<String, String> replaceValue = Maps.newHashMap();
        this.username = username;
        this.password = password;
        this.dbName =dbName;
        // 目前只支持mysql
        if (DbTypeEnum.MYSQL.getDriverClass().equals(driverType)) {
            this.driverClass = DbTypeEnum.MYSQL.getDriverClass();
            // 解析url ip port
            URI uri = URI.create("http://"+dbIpPort);
            this.host = uri.getHost();
            this.port = uri.getPort();
            this.dsKey  = host+"|"+port+"|"+dbName+"|"+username+"|"+password;
            replaceValue.put("dbIpPort" , dbIpPort);
            replaceValue.put("dbName" ,dbName);
            this.dbUrl = CsStringUtil.strSubReplace(replaceValue,DbTypeEnum.MYSQL.getUrl());
        }
        return this;
    }

}
