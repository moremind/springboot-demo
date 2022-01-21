package cn.moremind.spring.springbootkafka.controller;

import cn.moremind.spring.springbootkafka.config.ProducerConfigurations;
import cn.moremind.spring.springbootkafka.pojo.Message;
import cn.moremind.spring.springbootkafka.request.MessageRequest;
import cn.moremind.spring.springbootkafka.response.ResponseUtil;
import cn.moremind.spring.springbootkafka.service.impl.SenderImpl;
import cn.moremind.spring.springbootkafka.util.DateUtil;
import cn.moremind.spring.springbootkafka.util.LogUtil;
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
