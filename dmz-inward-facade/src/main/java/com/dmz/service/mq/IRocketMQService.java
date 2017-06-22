package com.dmz.service.mq;

/**
 * Created by dmz on 2016/3/5.
 */
public interface IRocketMQService {
    public void sendMessage(String topic, String tag, String key, String message);
}
