package com.dmz.service.mq.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;
import java.util.List;

/**
 * Created by dmz on 2016/3/7.
 * @tag MQ监听器
 */
public class DConsumerListern extends DMessageListenerConcurrently {

    @Override
    public void consume(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
        MessageExt message = messages.get(0);
        if ("dmz-inward".equals(message.getTopic())){
            System.out.println(new String(message.getBody()));
        }
    }
}
