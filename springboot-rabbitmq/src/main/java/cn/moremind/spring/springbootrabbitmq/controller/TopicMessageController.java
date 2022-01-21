package cn.moremind.spring.springbootrabbitmq.controller;

import cn.moremind.spring.springbootrabbitmq.service.impl.TopicSenderService;
import cn.moremind.spring.springbootrabbitmq.dto.MessageRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/msg/topic")
public class TopicMessageController {

    @Autowired
    TopicSenderService topicSenderService;

    @PostMapping("/one")
    public void sendTopicMessageOne(@NotNull @RequestBody MessageRequestDTO msg) {
        topicSenderService.sendMsg(msg.getMsg());
    }

    @PostMapping("/two")
    public void sendTopicMessageTwo(@NotNull @RequestBody MessageRequestDTO msg) {
        topicSenderService.sendMsg2(msg.getMsg());
    }
}
