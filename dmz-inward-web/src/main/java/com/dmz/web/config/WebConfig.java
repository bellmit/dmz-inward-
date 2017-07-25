//package com.dmz.web.config;
//
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import org.hibernate.validator.HibernateValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.http.converter.*;
//import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.nio.charset.StandardCharsets.UTF_8;
//
///**
// * @author dmz
// * @date 2017/7/20
// */
////@Configuration
////@ComponentScan(basePackages = {"com.dmz.web"})
//public class WebConfig {
//
//    @Bean
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
//        requestMappingHandlerMapping.setUseSuffixPatternMatch(true);
//        //requestMappingHandlerMapping.setInterceptors();
//        return requestMappingHandlerMapping;
//    }
//
//    @Bean
//    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
//        RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
//        requestMappingHandlerAdapter.setMessageConverters(messageConverters());
//        return requestMappingHandlerAdapter;
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setProviderClass(HibernateValidator.class);
//        validator.setValidationMessageSource(resourceBundleMessageSource());
//        return validator;
//    }
//
//    @Bean
//    public ResourceBundleMessageSource resourceBundleMessageSource() {
//        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
//        resourceBundleMessageSource.setUseCodeAsDefaultMessage(false);
//        resourceBundleMessageSource.setBasenames("classpath:/messages");
//        return resourceBundleMessageSource;
//    }
//
//    private List<HttpMessageConverter<?>> messageConverters() {
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        ByteArrayHttpMessageConverter byteArray = new ByteArrayHttpMessageConverter();
//        StringHttpMessageConverter string = new StringHttpMessageConverter(UTF_8);
//        ResourceHttpMessageConverter resource = new ResourceHttpMessageConverter();
//        FormHttpMessageConverter form = new FormHttpMessageConverter();
//        Jaxb2RootElementHttpMessageConverter jaxb2RootElement = new Jaxb2RootElementHttpMessageConverter();
//        FastJsonHttpMessageConverter fastJson = new FastJsonHttpMessageConverter();
//        messageConverters.add(byteArray);
//        messageConverters.add(string);
//        messageConverters.add(resource);
//        messageConverters.add(form);
//        messageConverters.add(jaxb2RootElement);
//        messageConverters.add(fastJson);
//        return messageConverters;
//    }
//}
