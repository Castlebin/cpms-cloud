package com.cpms.auth.config;

import com.cpms.auth.common.constants.SystemConstant;
import com.cpms.common.core.factory.YmlResourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import java.io.IOException;
import java.util.Objects;

/**
 * @description: 自定义环境处理类，在运行SpringApplication之前加载任意配置文件到Environment环境中
 * 用法，不需要在使用@PropertySource来加载自定义配置文件，可以直接使用@Value注解获取
 * @author: gulang
 * @time: 2021/7/6 16:02
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {
    /**
     * 自定义配置文件
     */
    private final String[] profiles = SystemConstant.CUSTOM_PROPS_FILE_NAME.split(",");
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        System.out.println("-----load custom resource file-------");
        //循环添加
        for (String profile : profiles) {
            //从classpath路径下面查找文件
            Resource resource = new ClassPathResource(profile);
            //加载成PropertySource对象，并添加到Environment环境中
            PropertySource<?> propertySource = loadProfiles(resource);
            if(Objects.nonNull(propertySource)) {
                environment.getPropertySources().addLast(propertySource);
            }
        }
    }

    //加载单个配置文件
    private PropertySource<?> loadProfiles(Resource resource) {
        if (!resource.exists()) {
            throw new IllegalArgumentException("资源文件 " + resource + " 不存在");
        }
        try {
            String sourceName = resource.getFilename();
            // 对自定义yml配置文件加载
            YmlResourceFactory ymlResourceFactory = new YmlResourceFactory();
            return ymlResourceFactory.createPropertySource(sourceName,new EncodedResource(resource));
        }catch (IOException ex) {
            throw new IllegalStateException("加载配置文件失败" + resource, ex);
        }
    }
}
