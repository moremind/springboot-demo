package com.javanorth.spring.mybatisplus.controller;

import com.javanorth.spring.mybatisplus.dao.UserDetailDao;
import com.javanorth.spring.mybatisplus.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserDetailDao userDetailDao;

    @GetMapping("/test")
    public String test() {
        return "request success";
    }

    @RequestMapping("/userList")
    public List testList() {
        return userDetailDao.selectList(null);
    }


}
