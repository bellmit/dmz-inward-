package com.dmz.service.mq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by dmz on 2016/3/5.
 */
public class RocketMQProducer extends DefaultMQProducer implements InitializingBean, DisposableBean {

    public void destroy() throws Exception {
        this.destroy();
    }

    public void afterPropertiesSet() throws Exception {
        this.start();
    }
}
