package com.dmz.basic.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author dmz
 * @date 2017/8/25
 */
@Configuration
public class SpringRedisConfig {

    @Value("${redis.config.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.config.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.config.minIdle}")
    private Integer minIdle;

    @Value("${redis.config.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.config.hostName}")
    private String hostName;

    @Value("${redis.config.passwd}")
    private String passwd;

    @Value("${redis.config.port}")
    private Integer port;


    public @Bean
    RedissonClient redissonClient(){
        Config config = new Config();
        SingleServerConfig singleServer = config.useSingleServer();
        singleServer.setAddress("redis://8lovelife.com:6379");
//        singleServer.setPassword(passwd);
        //singleServer.setConnectTimeout();
        singleServer.setTimeout(10000);
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

    public @Bean
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    public @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPassword(passwd);
        jedisConnectionFactory.setPort(port);
        return jedisConnectionFactory;
    }

    public @Bean
    RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
