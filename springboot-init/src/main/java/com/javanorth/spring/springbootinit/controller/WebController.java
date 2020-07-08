package com.javanorth.spring.springbootinit.controller;

import com.javanorth.spring.springbootinit.entity.Person;
import com.javanorth.spring.springbootinit.exception.ServiceException;
import com.javanorth.spring.springbootinit.response.ResponseUtil;
import com.javanorth.spring.springbootinit.util.LogUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/test")
    public ResponseUtil test(HttpServletRequest request) {
        LogUtil.info(WebController.class, Thread.currentThread().getName());
        Person person = Person.builder().name("test")
                .age(11)
                .address("test")
                .build();
        return ResponseUtil.success(request.getRequestURI());
    }
}
