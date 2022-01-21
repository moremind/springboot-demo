package cn.moremind.spring.springbootsecurity.controller;

import cn.moremind.spring.springbootsecurity.request.UserDetailRequest;
import cn.moremind.spring.springbootsecurity.response.ResponseResult;
import cn.moremind.spring.springbootsecurity.service.UserAuth;
import cn.moremind.spring.springbootsecurity.response.ResponseUtil;
import cn.moremind.spring.springbootsecurity.service.impl.TokenServiceImpl;
import cn.moremind.spring.springbootsecurity.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    TokenServiceImpl tokenService;

    @Autowired
    UserAuth userAuth;

    @PostMapping("/login")
    public ResponseUtil login(@RequestBody UserDetailRequest userDetailRequest, HttpServletRequest request) {
        LogUtil.info(UserDetailController.class, "request params: {}", userDetailRequest);
        String token = userAuth.login(userDetailRequest.getUsername(), userDetailRequest.getPassword());
        LogUtil.info(UserDetailController.class, "token: {}", token);
        return ResponseUtil.success(ResponseResult.LOGIN_SUCCESS.getCode(), ResponseResult.LOGIN_SUCCESS.getMsg(),
                request.getRequestURI(), token);
    }

    @PostMapping("/deal")
    public ResponseUtil deal(@RequestParam String token, HttpServletRequest request) {
        LogUtil.info(UserDetailController.class, "request params: {}", token);
        tokenService.parseToken(token);
        return ResponseUtil.success(request.getRequestURI());
    }

    @GetMapping("/test")
    public void test() {
        LogUtil.info(this.getClass(), "test----");
    }
}
