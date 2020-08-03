package com.javanorth.spring.springbootshiro.controller;

import com.javanorth.spring.springbootshiro.response.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseUtil test(HttpServletRequest request) {
        return ResponseUtil.success(request.getRequestURI());
    }
}
