package com.cpms.system.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.cpms.common.constant.AppConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: mybatisPlus配置
 * @author: gulang
 * @time: 2021/7/16 16:53
 */
@Configuration
@MapperScan(AppConstant.BASE_PACKAGES+".**.modouls.**.mapper") //扫描mapper包 避免在每个mapper类上加@Mapper注解
public class MybatisPlusConfig {

    /**
     * 分页插件配置
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }
}
