package com.javanorth.spring.springbootjapidocs.controller;


import com.javanorth.spring.springbootjapidocs.request.TestRequest;
import com.javanorth.spring.springbootjapidocs.response.ResponseResult;
import com.javanorth.spring.springbootjapidocs.response.ResponseUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @RequestMapping("/test")
    public ResponseUtil test(HttpServletRequest request, @RequestBody TestRequest testRequest) {
        return ResponseUtil.success(request.getRequestURI(), testRequest);
    }
}
