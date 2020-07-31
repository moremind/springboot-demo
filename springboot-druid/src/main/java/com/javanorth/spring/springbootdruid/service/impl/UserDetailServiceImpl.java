package com.javanorth.spring.springbootdruid.service.impl;


import com.javanorth.spring.springbootdruid.dao.UserDetailDao;
import com.javanorth.spring.springbootdruid.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;
}
