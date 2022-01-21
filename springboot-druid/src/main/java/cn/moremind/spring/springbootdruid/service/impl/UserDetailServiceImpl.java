package cn.moremind.spring.springbootdruid.service.impl;


import cn.moremind.spring.springbootdruid.dao.UserDetailDao;
import cn.moremind.spring.springbootdruid.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;
}
