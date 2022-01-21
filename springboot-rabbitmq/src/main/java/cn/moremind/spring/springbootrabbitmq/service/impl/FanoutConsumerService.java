package cn.moremind.spring.springbootrabbitmq.service.impl;

import com.google.gson.Gson;
import cn.moremind.spring.springbootrabbitmq.dto.MessageDTO;
import cn.moremind.spring.springbootrabbitmq.service.ConsumerService;
import cn.moremind.spring.springbootrabbitmq.util.LogUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FanoutConsumerService implements ConsumerService {

    @RabbitListener(queues = "${rabbitmqConfig.fanoutQueueOne}")
    @RabbitHandler
    @Override
    public void onMessage(String msg) {
        LogUtil.info(getClass(), "fanout queue one receive msg from mq", msg);
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);
    }

    @RabbitListener(queues = "${rabbitmqConfig.fanoutQueueTwo}")
    @RabbitHandler
    public void onMessage2(String msg) {
        LogUtil.info(getClass(), "fanout queue two receive msg from mq", msg);
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);
    }
}
