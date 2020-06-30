package com.javanorth.spring.springbootrabbitmq.controller;


import com.javanorth.spring.springbootrabbitmq.dto.MessageRequestDTO;
import com.javanorth.spring.springbootrabbitmq.service.impl.DirectSenderService;
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
