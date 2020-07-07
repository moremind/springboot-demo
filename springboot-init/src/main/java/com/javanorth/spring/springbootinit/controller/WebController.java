package com.javanorth.spring.springbootinit.controller;

import com.javanorth.spring.springbootinit.entity.Person;
import com.javanorth.spring.springbootinit.response.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/test")
    public ResponseUtil test() {
        Person person = Person.builder().name("test")
                .age(11)
                .address("test")
                .build();
        return ResponseUtil.success();
    }
}
