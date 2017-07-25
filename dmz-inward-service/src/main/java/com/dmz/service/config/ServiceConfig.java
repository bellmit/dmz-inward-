package com.dmz.service.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @author dmz
 * @date 2017/7/19
 */
//@Configuration
//@ComponentScan(basePackages = {"com.dmz.service"}, includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class)})
public class ServiceConfig {

    //@Bean
    //public AnnotationAwareAspectJAutoProxyCreator annotationAwareAspectJAutoProxyCreator() {
    //    return new AnnotationAwareAspectJAutoProxyCreator();
    //}

    @Bean
    public TransactionAttributeSource annotationTransactionAttributeSource() {
        return new AnnotationTransactionAttributeSource();
    }

    @Bean
    @Primary
    public TransactionInterceptor transactionInterceptor(@Qualifier(value = "transactionManager") PlatformTransactionManager transactionManager) {
        return new TransactionInterceptor(transactionManager, annotationTransactionAttributeSource());
    }


    @Bean
    public TransactionInterceptor transactionInterceptor2(@Qualifier(value = "transactionManager2") PlatformTransactionManager transactionManager) {
        return new TransactionInterceptor(transactionManager, annotationTransactionAttributeSource());
    }
}
