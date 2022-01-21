package cn.moremind.spring.springbootjpa.service.impl;

import cn.moremind.spring.springbootjpa.dao.UserDetailDao;
import cn.moremind.spring.springbootjpa.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;
}
