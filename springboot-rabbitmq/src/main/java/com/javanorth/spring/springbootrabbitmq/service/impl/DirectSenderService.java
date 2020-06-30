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
public class DirectSenderService implements SenderService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value(value = "${rabbitmqConfig.directQueue}")
    private String directQueue;

    /**
     *
     * @param msg
     */
    @Override
    public void sendMsg(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = MessageDTO.builder().date(DateUtil.getCurrentTime())
                .msgId(UUID.randomUUID().toString())
                .msg(msg)
                .build();
        rabbitTemplate.convertAndSend(directQueue, gson.toJson(messageDTO));
    }
}
