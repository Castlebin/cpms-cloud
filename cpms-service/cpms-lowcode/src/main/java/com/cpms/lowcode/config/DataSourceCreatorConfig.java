package com.cpms.lowcode.config;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.HikariDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.hikari.HikariCpConfig;
import com.cpms.framework.common.exception.BizException;
import com.cpms.lowcode.common.enums.RespResultEnum;
import com.cpms.lowcode.modules.dynamic.bo.DynamicDbInfoBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @description:
 * @author: gulang
 * @time: 2022/1/28 15:02
 */
@Component
@Slf4j
public class DataSourceCreatorConfig {
    private  static volatile DynamicRoutingDataSource dynamicRoutingDataSource;
    /**
     * 创建数据源
     * @param dynamicDbInfoBO
     * @return
     */
    public static DataSource createDataSource(DynamicDbInfoBO dynamicDbInfoBO){
        // 配置Hikari参数
        HikariCpConfig hikariCpConfig = new HikariCpConfig();
        hikariCpConfig.setDriverClassName(dynamicDbInfoBO.getDriverClass());
        hikariCpConfig.setIsAutoCommit(true);
        hikariCpConfig.setConnectionTestQuery("SELECT 1");
        hikariCpConfig.setConnectionTimeout(10000L);
        hikariCpConfig.setIdleTimeout(30000L);
        hikariCpConfig.setMaxLifetime(900000L);
        hikariCpConfig.setMaxPoolSize(30);
        hikariCpConfig.setMinIdle(10);
        hikariCpConfig.setPoolName("Hikari-Dynamic");
        hikariCpConfig.setValidationTimeout(1000L);
        HikariDataSourceCreator hikariDataSourceCreator = new HikariDataSourceCreator(hikariCpConfig);
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.setUsername(dynamicDbInfoBO.getUsername());
        dataSourceProperty.setPassword(dynamicDbInfoBO.getPassword());
        dataSourceProperty.setUrl(dynamicDbInfoBO.getDbUrl());
        dataSourceProperty.setLazy(false);
        try {
            return hikariDataSourceCreator.createDataSource(dataSourceProperty);
        }catch (Exception e) {
            log.error("创建数据库连接失败",e);
            throw new BizException(RespResultEnum.CREAT_DATABASE_CONNECT_FAIL_ERROR);
        }
    }

    /**
     * 初始化动态数据源
     * @param dynamicDbInfoBO
     */
    public void initDynamicDataSource(DynamicDbInfoBO dynamicDbInfoBO){
        DataSource dataSource = null;
        DynamicRoutingDataSource dynamicRoutingDataSource = getDynamicRoutingDataSource();
        try {
            dataSource = dynamicRoutingDataSource.getDataSource(dynamicDbInfoBO.getDsKey());
        } catch (RuntimeException e) {
            // 严格校验情况不匹配抛错 重新加载
        }
        if(Objects.isNull(dataSource)){
            dataSource = DataSourceCreatorConfig.createDataSource(dynamicDbInfoBO);
            if(Objects.nonNull(dataSource)){
                dynamicRoutingDataSource.addDataSource(dynamicDbInfoBO.getDsKey(), dataSource);
            }
        }
        if(Objects.isNull(dataSource)){
            throw new BizException("无法获取DataSourc数据源");
        }
    }

    public DynamicRoutingDataSource getDynamicRoutingDataSource(){
        // 双重锁校验单例
        if(dynamicRoutingDataSource == null){
            synchronized (DynamicRoutingDataSource.class){
                if(dynamicRoutingDataSource == null){
                    dynamicRoutingDataSource = new DynamicRoutingDataSource();
                }
            }
        }
        return dynamicRoutingDataSource;
    }
}
