package com.javanorth.spring.springbootkafka.controller;

import com.javanorth.spring.springbootkafka.config.ProducerConfigurations;
import com.javanorth.spring.springbootkafka.pojo.Message;
import com.javanorth.spring.springbootkafka.request.MessageRequest;
import com.javanorth.spring.springbootkafka.response.ResponseUtil;
import com.javanorth.spring.springbootkafka.service.impl.SenderImpl;
import com.javanorth.spring.springbootkafka.util.DateUtil;
import com.javanorth.spring.springbootkafka.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class KafkaController {

    @Autowired
    SenderImpl sender;

    @RequestMapping("/send")
    public ResponseUtil testSend(HttpServletRequest request, @RequestBody MessageRequest messageRequest) {
        LogUtil.info(this.getClass(), "config: {}", ProducerConfigurations.getBootstrapServersConfig());
        Message message = Message.builder().msgId(UUID.randomUUID().toString().replace("-",""))
                .msg(messageRequest.getMessage())
                .date(DateUtil.getCurrentTime())
                .build();
        sender.sendMsg(message);
        return ResponseUtil.success(request.getRequestURI());
    }
}
