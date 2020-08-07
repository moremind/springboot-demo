package com.javanorth.spring.springbootshiro.controller;

import com.javanorth.spring.springbootshiro.request.UserDetailRequest;
import com.javanorth.spring.springbootshiro.response.ResponseResult;
import com.javanorth.spring.springbootshiro.response.ResponseUtil;
import com.javanorth.spring.springbootshiro.service.impl.UserDetailServiceImpl;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import org.apache.shiro.SecurityUtils;
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
        userDetailService.userLogin(userDetailRequest.getUsername(), userDetailRequest.getPassword());
        return ResponseUtil.success(ResponseResult.LOGIN_SUCCESS.getCode(), ResponseResult.LOGIN_SUCCESS.getMsg(),
                request.getRequestURI());
    }

    /**
     * judge auth
     * @param request
     * @return
     */
    @GetMapping("/judgeAuth")
    public ResponseUtil judgeAuth(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            LogUtil.info(this.getClass(), "auth success");
            return ResponseUtil.success(ResponseResult.AUTH_SUCCESS.getCode(), ResponseResult.AUTH_SUCCESS.getMsg(),
                    request.getRequestURI());
        } else {
            LogUtil.info(this.getClass(), "auth failed");
            return ResponseUtil.success(ResponseResult.AUTH_ERROR.getCode(), ResponseResult.AUTH_ERROR.getMsg(),
                    request.getRequestURI());
        }
    }

    /**
     * judge auth
     * @param request
     * @return
     */
    @GetMapping("/judgeRole")
    public ResponseUtil judgeRoles(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            LogUtil.info(this.getClass(), "admin success");
            return ResponseUtil.success(ResponseResult.AUTH_SUCCESS.getCode(), ResponseResult.AUTH_SUCCESS.getMsg(),
                    request.getRequestURI());
        } else {
            LogUtil.info(this.getClass(), "admin failed");
            return ResponseUtil.success(ResponseResult.AUTH_ERROR.getCode(), ResponseResult.AUTH_ERROR.getMsg(),
                    request.getRequestURI());
        }
    }
}
