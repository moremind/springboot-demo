package com.javanorth.spring.springbootkafka.service.impl;

import com.google.gson.Gson;
import com.javanorth.spring.springbootkafka.pojo.Message;
import com.javanorth.spring.springbootkafka.util.LogUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerImpl {

    @KafkaListener(id = "client-1", topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(String message) {
        Gson gson = new Gson();
        Message msg = gson.fromJson(message, Message.class);

        LogUtil.info(ConsumerImpl.class, "receive message: {}", message);
        LogUtil.info(ConsumerImpl.class, "receive msg: {}", msg.getMsg());
    }

    @KafkaListener(id = "client-2", topics = "${kafka.topic.name}")
    public void onConsumerMessage(String message) {
        Gson gson = new Gson();
        Message msg = gson.fromJson(message, Message.class);

        LogUtil.info(ConsumerImpl.class, "receive message2: {}", message);
        LogUtil.info(ConsumerImpl.class, "receive msg2: {}", msg.getMsg());
    }


}
