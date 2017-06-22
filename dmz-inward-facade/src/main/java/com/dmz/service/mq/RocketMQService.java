package com.dmz.service.mq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import javax.annotation.Resource;

/**
 * Created by dmz on 2016/3/5.
 */
public class RocketMQService implements IRocketMQService {

    @Resource(name = "rocketMQ")
    private RocketMQProducer rocketMQProducer;

    public void sendMessage(String topic, String tag, String key, String message) {

        Message info = new Message(topic, tag, key, message.getBytes());

        try {
            SendResult result = rocketMQProducer.send(info);
            System.out.println(result);

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
