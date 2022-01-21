package cn.moremind.spring.springbootrabbitmq.service.impl;

import com.google.gson.Gson;
import cn.moremind.spring.springbootrabbitmq.dto.MessageDTO;
import cn.moremind.spring.springbootrabbitmq.service.SenderService;
import cn.moremind.spring.springbootrabbitmq.util.DateUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FanoutSenderService implements SenderService {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 广播模式交换机
     */
    @Value(value = "${rabbitmqConfig.fanoutExchange}")
    private String fanoutExchange;

    /**
     * 通过fanout发送广播消息，广播消息会发送到每个绑定的队列
     * @param msg 需要发送的消息
     */
    @Override
    public void sendMsg(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = MessageDTO.builder().date(DateUtil.getCurrentTime())
                .msgId(UUID.randomUUID().toString())
                .msg(msg)
                .build();
        rabbitTemplate.convertAndSend(fanoutExchange, "", gson.toJson(messageDTO));
    }

}
