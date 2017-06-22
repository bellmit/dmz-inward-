package com.dmz.test.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dmz on 2016/12/9.
 */
@Configuration
public class ConfigurationBeans2 {

    @Bean
    public ConfigurationBean cbean2() {
        return new ConfigurationBean();
    }
}
