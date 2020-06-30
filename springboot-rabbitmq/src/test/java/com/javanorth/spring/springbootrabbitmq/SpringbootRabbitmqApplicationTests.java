package com.javanorth.spring.springbootrabbitmq;

import com.google.gson.Gson;
import com.javanorth.spring.springbootrabbitmq.dto.MessageDTO;
import com.javanorth.spring.springbootrabbitmq.service.impl.DirectSenderService;
import com.javanorth.spring.springbootrabbitmq.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {
    @Autowired
    DirectSenderService directSenderService;

    @Test
    void contextLoads() {
        Gson gson = new Gson();
        MessageDTO messageDTO = MessageDTO.builder().date(DateUtil.getCurrentTime())
                .msgId(UUID.randomUUID().toString())
                .msg("1111").build();
        System.out.println(gson.toJson(messageDTO));
        System.out.println(DateUtil.getCurrentTime());
    }

    /**
     * direct模式一对多发送，接受者将均匀的收到信息
     */
    @Test
    public void oneToMany() {
        for (int i=0;i<100;i++){
            directSenderService.sendMsg(String.valueOf(i));
        }
    }

}
