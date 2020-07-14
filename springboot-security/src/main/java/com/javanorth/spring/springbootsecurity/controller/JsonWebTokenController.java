package com.javanorth.spring.springbootsecurity.controller;

import com.javanorth.spring.springbootsecurity.request.UserDetailRequest;
import com.javanorth.spring.springbootsecurity.response.ResponseUtil;
import com.javanorth.spring.springbootsecurity.util.LogUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JsonWebTokenController {

    /**
     * 测试接口
     * @param request 默认的request
     * @return 默认响应
     */
    @GetMapping("/test")
    public ResponseUtil test(HttpServletRequest request) {
        return ResponseUtil.success(request.getRequestURI());
    }

}
