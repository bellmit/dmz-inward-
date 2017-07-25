package com.dmz.service.utils.config;


import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/7/20
 */
//@Configuration
public class UtilConfig {

    @Bean
    public  PropertiesFactoryBean propertiesFactoryBean() throws IOException {
        PropertiesFactoryBean propertiesF = new PropertiesFactoryBean();
        propertiesF.setFileEncoding("utf-8");
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        propertiesF.setLocations(resourcePatternResolver.getResources("classpath:data.properties"));
        return propertiesF;
    }

    @Bean
    public  PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer configProperty = new PropertyPlaceholderConfigurer();
        configProperty.setIgnoreResourceNotFound(false);
        configProperty.setProperties(propertiesFactoryBean().getObject());
        return configProperty;
    }
}
