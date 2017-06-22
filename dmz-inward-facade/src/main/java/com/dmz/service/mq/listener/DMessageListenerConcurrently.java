package com.dmz.service.mq.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import java.util.List;

/**
 * Created by dmz on 2016/3/7.
 *
 * @tag 扩展messagelistern
 */
public abstract class DMessageListenerConcurrently implements MessageListenerConcurrently {

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        this.consume(list, consumeConcurrentlyContext);
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    public abstract void consume(List<MessageExt> messages, ConsumeConcurrentlyContext context);
}
