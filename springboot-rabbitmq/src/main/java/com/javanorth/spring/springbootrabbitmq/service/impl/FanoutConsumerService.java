package com.javanorth.spring.springbootrabbitmq.service.impl;

import com.google.gson.Gson;
import com.javanorth.spring.springbootrabbitmq.dto.MessageDTO;
import com.javanorth.spring.springbootrabbitmq.service.ConsumerService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FanoutConsumerService implements ConsumerService {

    @RabbitListener(queues = "${rabbitmqConfig.fanoutQueueOne}")
    @RabbitHandler
    @Override
    public void onMessage(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);
        System.out.println("fanout queue one receive rabbitmq message:" + msg);
    }

    @RabbitListener(queues = "${rabbitmqConfig.fanoutQueueTwo}")
    @RabbitHandler
    public void onMessage2(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);
        System.out.println("fanout queue two receive rabbitmq message:" + msg);
    }
}
