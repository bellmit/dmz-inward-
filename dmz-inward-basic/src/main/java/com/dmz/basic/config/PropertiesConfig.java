package com.dmz.basic.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * @author dmz
 * @date 2017/8/17
 */
@Configuration
public class PropertiesConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        propertyPlaceholderConfigurer.setLocations(resolver.getResource("classpath:data.properties"));
        return propertyPlaceholderConfigurer;
    }

}
