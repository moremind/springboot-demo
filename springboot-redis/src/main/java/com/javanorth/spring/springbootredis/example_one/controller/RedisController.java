package com.javanorth.spring.springbootredis.example_one.controller;


import com.javanorth.spring.springbootredis.example_one.dto.RedisDTO;
import com.javanorth.spring.springbootredis.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    RedisUtils redisUtils;

    @RequestMapping("/set")
    public void setValue(@RequestBody RedisDTO redisDTO) {
        redisUtils.set(redisDTO.getId(), redisDTO.getValue());

    }

    @RequestMapping("/get")
    public String getValue(@RequestParam String id) {
        System.out.println("key:" + id);
        return redisUtils.get(id);
    }



}
