package com.cpms.framework.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.cpms.framework.common.utils.CsSecureUtil;
import com.cpms.framework.mybatis.plugins.SqlLogInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * @description: 自动填充字段信息配置
 * @author: gulang
 * @time: 2021/7/16 16:53
 */
@EnableTransactionManagement // 开启事务
@MapperScan("com.cpms.**.mapper.**") //扫描mapper包 避免在每个mapper类上加@Mapper注解
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 分页插件配置
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     *  插入时填充字段
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("createBy", CsSecureUtil.userAccount(),metaObject);
        this.setFieldValByName("updateBy", CsSecureUtil.userAccount(),metaObject);
    }

    /**
     * 更新时填充字段
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("updateBy", CsSecureUtil.userAccount(),metaObject);
    }

//    /**
//     * sql 日志
//     *
//     * @return SqlLogInterceptor
//     */
//    @Bean
//    @ConditionalOnProperty(value = "mybatis-plus.show-sql-log", havingValue = "true")
//    public SqlLogInterceptor sqlLogInterceptor() {
//        System.out.println("打印sql日志信息");
//        return new SqlLogInterceptor();
//    }
}
