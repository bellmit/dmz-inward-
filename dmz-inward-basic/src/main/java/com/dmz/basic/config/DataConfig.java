package com.dmz.basic.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author dmz
 * @date 2017/7/17
 */
//@Configuration
//@MapperScan(value = "com.dmz.basic.mapper")
public class DataConfig {

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));
        sessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sessionFactory.setDataSource(dataSource());
        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://mysql:3306/base?useUnicode=true&amp;characterEncoding=utf8");
            config.setUsername("base");
            config.setPassword("base123");
            config.addDataSourceProperty("cachePrepStmts", true);
            config.addDataSourceProperty("prepStmtCacheSize", 250);
            config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
            config.addDataSourceProperty("useServerPrepStmts", true);
            config.addDataSourceProperty("useLocalSessionState", true);
            config.addDataSourceProperty("useLocalTransactionState", true);
            config.addDataSourceProperty("rewriteBatchedStatements", true);
            config.addDataSourceProperty("cacheResultSetMetadata", true);
            config.addDataSourceProperty("cacheServerConfiguration", true);
            config.addDataSourceProperty("elideSetAutoCommits", true);
            config.addDataSourceProperty("maintainTimeStats", false);
            HikariDataSource dataSource = new HikariDataSource(config);
            return dataSource;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Bean(name = "transactionManager2")
    public DataSourceTransactionManager transactionManager2() {
        return new DataSourceTransactionManager(dataSource2());
    }

    @Bean
    public DataSource dataSource2() {
        HikariDataSource dataSource = new HikariDataSource();
        try {
            dataSource.setJdbcUrl("jdbc:mysql://mysql:3306/base?useUnicode=true&amp;characterEncoding=utf8");
            dataSource.setUsername("dbbackup");
            dataSource.setPassword("wzo8QtV0FqlbmYwUVsyD");
            dataSource.addDataSourceProperty("cachePrepStmts", "true");
            dataSource.addDataSourceProperty("prepStmtCacheSize", "250");
            dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        } catch (Exception e) {
            e.getMessage();
        }
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/*.xml"));
        sessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sessionFactory.setDataSource(dataSource2());
        return sessionFactory.getObject();
    }

}
