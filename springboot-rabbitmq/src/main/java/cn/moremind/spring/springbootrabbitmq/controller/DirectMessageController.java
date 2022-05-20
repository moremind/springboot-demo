package cn.moremind.spring.springbootrabbitmq.controller;


import cn.moremind.spring.springbootrabbitmq.dto.MessageRequestDTO;
import cn.moremind.spring.springbootrabbitmq.service.impl.DirectSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/msg/direct")
public class DirectMessageController {

    @Autowired
    DirectSenderService directSenderService;

    @PostMapping("/sendMsg")
    public void sendDirectMessage(@NotNull @RequestBody MessageRequestDTO msg) {
        directSenderService.sendMsg(msg.getMsg());
    }

}