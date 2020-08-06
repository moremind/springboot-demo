package com.javanorth.spring.springbootshiro.controller;

import com.javanorth.spring.springbootshiro.request.UserDetailRequest;
import com.javanorth.spring.springbootshiro.response.ResponseResult;
import com.javanorth.spring.springbootshiro.response.ResponseUtil;
import com.javanorth.spring.springbootshiro.service.impl.UserDetailServiceImpl;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    UserDetailServiceImpl userDetailService;

    /**
     * user register api
     * @param request
     * @param userDetailRequest
     * @return
     */
    @PostMapping("/register")
    public ResponseUtil userRegister(HttpServletRequest request, @RequestBody UserDetailRequest userDetailRequest) {
        userDetailService.userRegister(userDetailRequest.getUsername(), userDetailRequest.getPassword());
        return ResponseUtil.success(request.getRequestURI());
    }

    /**
     * user login api
     * @param request
     * @param userDetailRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseUtil userLogin(HttpServletRequest request, @RequestBody UserDetailRequest userDetailRequest) {
        boolean flag = userDetailService.userLogin(userDetailRequest.getUsername(), userDetailRequest.getPassword());
        if (flag) {
            return ResponseUtil.success(ResponseResult.LOGIN_SUCCESS.getCode(), ResponseResult.LOGIN_SUCCESS.getMsg(),
                    request.getRequestURI());
        } else {
            return ResponseUtil.success(ResponseResult.LOGIN_FAILED.getCode(), ResponseResult.LOGIN_FAILED.getMsg(),
                    request.getRequestURI());
        }
    }

    @PostMapping("/shiroLogin")
    public void login(HttpServletRequest request, @RequestBody UserDetailRequest userDetailRequest) {
        Subject subject = SecurityUtils.getSubject();
        String md5Pwd = new SimpleHash("MD5", userDetailRequest.getPassword(), "b26ac" , 1).toString();
        UsernamePasswordToken token = new UsernamePasswordToken(userDetailRequest.getUsername(), userDetailRequest.getPassword());
        subject.login(token);
    }




    @GetMapping("/test")

    public ResponseUtil test(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        LogUtil.info(this.getClass(),"{}", subject.getSession());
        LogUtil.info(this.getClass(),"{}", subject.isRunAs());

//        LogUtil.info(TestController.class, "test:::::::--++++++++---:{}", userDetail1);
//        LogUtil.info(TestController.class, "test:::::::-----:{}", UUID.randomUUID().toString().replace("-", ""));
//        String result = new SimpleHash("MD5", userDetail.getPassword(), userDetail.getSalt(), 1).toString();
//        LogUtil.info(TestController.class, "test:::::::-----:{}", result);
//        Subject subject = SecurityUtils.getSubject();
//        subject.hasRole("Administrator");
//        if (subject.isAuthenticated()) {
//            LogUtil.info(TestController.class, "test:::::::-----");
//        } else {
//            LogUtil.info(TestController.class, "test:::++++=::::-----");
//        }
//        LogUtil.trace(TestController.class, "test:::::::-----");
        return ResponseUtil.success(request.getRequestURI());
    }
}
