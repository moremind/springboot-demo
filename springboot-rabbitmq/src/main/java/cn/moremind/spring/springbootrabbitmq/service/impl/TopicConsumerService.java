package cn.moremind.spring.springbootrabbitmq.service.impl;

import com.google.gson.Gson;
import cn.moremind.spring.springbootrabbitmq.dto.MessageDTO;
import cn.moremind.spring.springbootrabbitmq.service.ConsumerService;
import cn.moremind.spring.springbootrabbitmq.util.LogUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumerService implements ConsumerService {

    /**
     * topic模式下消费者1的对mq消息的消费
     * @param msg 从mq队列1中收到的消息
     */
    @RabbitListener(queues = "${rabbitmqConfig.topicQueueOne}")
    @RabbitHandler
    @Override
    public void onMessage(String msg) {
        LogUtil.info(getClass(),"topic queue one receive rabbitmq message:", msg);
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);

    }


    /**
     * topic模式下消费者2的对mq消息的消费
     * @param msg 从mq队列1中收到的消息
     */
    @RabbitListener(queues = "${rabbitmqConfig.topicQueueTwo}")
    @RabbitHandler
    public void onMessageTwo(String msg) {
        LogUtil.info(getClass(),"topic queue two receive rabbitmq message:", msg);
        Gson gson = new Gson();
        MessageDTO messageDTO = gson.fromJson(msg, MessageDTO.class);

    }

}
