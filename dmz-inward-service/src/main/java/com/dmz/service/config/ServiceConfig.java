package com.dmz.service.config;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author dmz
 * @date 2017/7/19
 */
@Configuration
@ComponentScan(basePackages = {"com.dmz.service.distributelock","com.dmz.service.advisor"},includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class)})
public class ServiceConfig {

    @Bean
    public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator() {
        return new AnnotationAwareAspectJAutoProxyCreator();
    }

    //@Bean
    //public TransactionAttributeSource annotationTransactionAttributeSource() {
    //    return new AnnotationTransactionAttributeSource();
    //}

    //@Bean
    //@Primary
    //public TransactionInterceptor transactionInterceptor(@Qualifier(value = "transactionManager") PlatformTransactionManager transactionManager) {
    //    return new TransactionInterceptor(transactionManager, annotationTransactionAttributeSource());
    //}
    //
    //
    //@Bean
    //public TransactionInterceptor transactionInterceptor2(@Qualifier(value = "transactionManager2") PlatformTransactionManager transactionManager) {
    //    return new TransactionInterceptor(transactionManager, annotationTransactionAttributeSource());
    //}


    @Bean  // 方法参数的bean验证
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setProxyTargetClass(true);
        return methodValidationPostProcessor;
    }
}
