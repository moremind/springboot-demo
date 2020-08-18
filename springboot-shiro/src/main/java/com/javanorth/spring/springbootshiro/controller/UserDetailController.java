package com.javanorth.spring.springbootshiro.controller;

import com.javanorth.spring.springbootshiro.request.UserDetailRequest;
import com.javanorth.spring.springbootshiro.response.ResponseResult;
import com.javanorth.spring.springbootshiro.response.ResponseUtil;
import com.javanorth.spring.springbootshiro.senum.AdminType;
import com.javanorth.spring.springbootshiro.senum.PermissionType;
import com.javanorth.spring.springbootshiro.service.impl.UserDetailServiceImpl;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
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

    @GetMapping("/logout")
    public ResponseUtil userLogout(HttpServletRequest request, String username) {
        Subject subject = SecurityUtils.getSubject();


        return ResponseUtil.success(request.getRequestURI());
    }


    /**
     * judge user permission
     * example:
     * 1. if (subject.isPermitted(xxx)) { // do things } else { //failed }
     * 2. use @RequiresPermissions(xxx)
     * @see RequiresPermissions
     * @param request
     * @return ResponseUtil
     */
    @GetMapping("/checkPermission")
    public ResponseUtil checkPermission(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted(PermissionType.USER_CREATE.getName())) {
            // do things
            LogUtil.info(this.getClass(), "this permission: {}", subject.isPermitted(PermissionType.USER_CREATE.getName()));
            return ResponseUtil.success(request.getRequestURI());
        } else {
            return ResponseUtil.fail(request.getRequestURI());
        }
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
            // do things
            LogUtil.info(this.getClass(), "auth success");
            return ResponseUtil.success(ResponseResult.AUTH_SUCCESS.getCode(), ResponseResult.AUTH_SUCCESS.getMsg(),
                    request.getRequestURI());
        } else {
            // failed
            LogUtil.info(this.getClass(), "auth failed");
            return ResponseUtil.success(ResponseResult.AUTH_ERROR.getCode(), ResponseResult.AUTH_ERROR.getMsg(),
                    request.getRequestURI());
        }
    }

    /**
     * judge roles
     * example:
     * 1. if (subject.hasRole(xxx)) { // do things } else { //failed }
     * 2. @RequiresRoles(xxx)
     * @param request
     * @return
     */
    @GetMapping("/judgeRole")
    public ResponseUtil judgeRoles(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(AdminType.ADMIN.getType())) {
            // do things
            LogUtil.info(this.getClass(), "admin success");
            return ResponseUtil.success(ResponseResult.AUTH_SUCCESS.getCode(), ResponseResult.AUTH_SUCCESS.getMsg(),
                    request.getRequestURI());
        } else {
            // failed
            LogUtil.info(this.getClass(), "admin failed");
            return ResponseUtil.success(ResponseResult.AUTH_ERROR.getCode(), ResponseResult.AUTH_ERROR.getMsg(),
                    request.getRequestURI());
        }
    }
}
