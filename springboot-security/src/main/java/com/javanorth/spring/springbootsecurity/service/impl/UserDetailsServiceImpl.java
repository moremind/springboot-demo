package com.javanorth.spring.springbootsecurity.service.impl;

import com.javanorth.spring.springbootsecurity.dao.UserDetailDao;
import com.javanorth.spring.springbootsecurity.dao.UserRoleDao;
import com.javanorth.spring.springbootsecurity.entity.UserDetail;
import com.javanorth.spring.springbootsecurity.exception.ExceptionEnum;
import com.javanorth.spring.springbootsecurity.exception.ServiceException;
import com.javanorth.spring.springbootsecurity.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.javanorth.spring.springbootsecurity.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailDao userDetailDao;

    @Autowired
    UserRoleDao userRoleDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        roleList.add(Role.builder().id(0).name("guest").build());
        UserDetail userDetail = userDetailDao.selectUserByUsername(username);
        Optional.ofNullable(userDetail).orElseThrow(() -> new ServiceException(ExceptionEnum.NULL_OF_USER_DETAIL.getRetCode(),
                 ExceptionEnum.NULL_OF_USER_DETAIL.getMessage()));
        List<Role> roles = userRoleDao.selectRolesByUserId(userDetail.getUid());
        LogUtil.info(this.getClass(), "user roles: {}", roles.toString());
        userDetail.setAuthorities(roles);
        return userDetail;
    }

}
