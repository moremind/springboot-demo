package com.javanorth.spring.springbootjpa.service.impl;

import com.javanorth.spring.springbootjpa.dao.UserDetailDao;
import com.javanorth.spring.springbootjpa.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;
}
