package cn.moremind.spring.springbootrabbitmq.service.impl;

import com.google.gson.Gson;
import cn.moremind.spring.springbootrabbitmq.dto.MessageDTO;
import cn.moremind.spring.springbootrabbitmq.service.ConsumerService;
import cn.moremind.spring.springbootrabbitmq.util.LogUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class DirectConsumerService implements ConsumerService {
    /**
     * direct模式一个对多个，一个对一个的发送和接收，接收者将均匀的接受，例如：3条，就1，2，1
     * @param msg
     */
    @RabbitListener(queues = "${rabbitmqConfig.directQueue}")
    @RabbitHandler
    @Override
    public void onMessage(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);
        LogUtil.info(getClass(),"receive rabbitmq message1:", msg);
    }

    @RabbitListener(queues = "${rabbitmqConfig.directQueue}")
    @RabbitHandler
    public void onMessage2(String msg) {
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);
        LogUtil.info(getClass(),"receive rabbitmq message2:", msg);
    }


}
