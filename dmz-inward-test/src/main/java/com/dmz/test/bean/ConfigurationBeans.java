package com.dmz.test.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by dmz on 2016/12/9.
 */
@Configuration
@Import(value = {ConfigurationBeans2.class})
@ImportResource(value = {"classpath:spring-beans.xml"})
public class ConfigurationBeans {

    @Bean(name = "cbean") //显式指定bean名字
    public ConfigurationBean getBean() {  //applicationContext引用的bean默认名字 则为方法名字
        return new ConfigurationBean();
    }
}
