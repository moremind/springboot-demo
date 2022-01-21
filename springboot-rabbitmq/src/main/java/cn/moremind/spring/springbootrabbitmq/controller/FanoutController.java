package cn.moremind.spring.springbootrabbitmq.controller;

import cn.moremind.spring.springbootrabbitmq.dto.MessageRequestDTO;
import cn.moremind.spring.springbootrabbitmq.service.impl.FanoutSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/msg/fanout")
public class FanoutController {

    @Autowired
    FanoutSenderService fanoutSenderService;

    @PostMapping("/sendMsg")
    public void sendMsg(@NotNull @RequestBody MessageRequestDTO msg) {
        fanoutSenderService.sendMsg(msg.getMsg());
    }
}
