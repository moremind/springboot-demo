package com.javanorth.spring.springbootrabbitmq.service.impl;

import com.google.gson.Gson;
import com.javanorth.spring.springbootrabbitmq.dto.MessageDTO;
import com.javanorth.spring.springbootrabbitmq.service.SenderService;
import com.javanorth.spring.springbootrabbitmq.util.DateUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TopicSenderService implements SenderService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发布订阅模式交换机
     */
    @Value(value = "${rabbitmqConfig.topicExchange}")
    private String topicExchange;

    /**
     * 发布订阅的路由1
     */
    @Value(value = "${rabbitmqConfig.topicRoutingKeyOne}")
    private String topicRoutingKeyOne;

    /**
     * 发布订阅路由2
     */
    @Value(value = "${rabbitmqConfig.topicRoutingKeyTwo}")
    private String topicRoutingKeyTwo;


    @Override
    public void sendMsg(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = MessageDTO.builder().date(DateUtil.getCurrentTime())
                .msgId(UUID.randomUUID().toString())
                .msg(msg)
                .build();
        rabbitTemplate.convertAndSend(topicExchange, topicRoutingKeyOne, gson.toJson(messageDTO));
    }

    public void sendMsg2(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = MessageDTO.builder().date(DateUtil.getCurrentTime())
                .msgId(UUID.randomUUID().toString())
                .msg(msg)
                .build();
        rabbitTemplate.convertAndSend(topicExchange, topicRoutingKeyTwo, gson.toJson(messageDTO));
    }


}
