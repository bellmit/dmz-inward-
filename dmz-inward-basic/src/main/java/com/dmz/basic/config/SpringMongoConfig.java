package com.dmz.basic.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.Arrays;

/**
 * @author dmz
 * @date 2017/8/16
 */
@Configuration
public class SpringMongoConfig {

    @Value("${mongo.username}")
    private String userName;

    @Value("${mongo.password}")
    private String password;

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private Integer port;

    @Value("${mongo.connectionsPerHost}")
    private Integer connectionsPerHost;

    @Value("${mongo.threadsAllowedToBlockForConnectionMultiplier}")
    private Integer threadsAllowedToBlockForConnectionMultiplier;

    @Value("${mongo.connectTimeout}")
    private Integer connectTimeout;

    @Value("${mongo.maxWaitTime}")
    private Integer maxWaitTime;

    @Value("${mongo.socketTimeout}")
    private Integer socketTimeout;

    @Value("${mongo.database}")
    private String databaseName;

    @Bean
    public MongoClient mongoClient() throws Exception {
        //.sslEnabled(true)
        MongoClientOptions.Builder builder = MongoClientOptions.builder();
        builder.connectionsPerHost(connectionsPerHost)
                .threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
                .connectTimeout(connectTimeout)
                .maxWaitTime(maxWaitTime)
                .socketTimeout(socketTimeout);
        MongoCredential credential = MongoCredential.createCredential(userName, databaseName, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential),builder.build());
        return mongoClient;
    }

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongoClient(), databaseName);
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
