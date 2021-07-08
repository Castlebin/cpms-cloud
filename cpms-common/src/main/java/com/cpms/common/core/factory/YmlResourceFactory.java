package com.cpms.common.core.factory;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @description: 自定义一个工厂类,使得@PropertySource注解支持自定义yml文件
 * 用法：在启动类加上注解@PropertySource(value = "classpath:system.yml", ignoreResourceNotFound = true,encoding = "utf-8", factory = YmlResourceFactory.class)
 * @author: gulang
 * @time: 2021/7/6 16:43
 */
public class YmlResourceFactory extends DefaultPropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        String sourceName = (name == null) ? resource.getResource().getFilename() : name;
        assert sourceName != null;
        if (sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
            YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
            factory.setResources(resource.getResource());
            factory.afterPropertiesSet();
            Properties properties = factory.getObject();
            assert properties != null;
            return new PropertiesPropertySource(sourceName, properties);
        }
        return super.createPropertySource(name, resource);
    }

}
