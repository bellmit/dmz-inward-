package com.dmz.service.mq.listener;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.MessageListener;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmz on 2016/3/7.
 *
 * @tag MQ Consumer
 */
public class RocketMQConsumer extends DefaultMQPushConsumer implements InitializingBean, DisposableBean {

    private Map<String, String> topics = new HashMap();

    public void destroy() throws Exception {
        this.destroy();
    }

    public void afterPropertiesSet() throws Exception {
        /**
         * 订阅topics
         */
        for (Map.Entry<String, String> entry : topics.entrySet()) {
            this.subscribe(entry.getKey(), entry.getValue());
        }

        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        this.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        this.registerMessageListener(this.getMessageListener());

        this.start();
    }

    public void registerMessageListener(MessageListener messageListener) {
        this.defaultMQPushConsumerImpl.registerMessageListener(messageListener);
    }

    public void setTopics(Map<String, String> topics) {
        this.topics = topics;
    }

    public Map<String, String> getTopics() {
        return topics;
    }
}
