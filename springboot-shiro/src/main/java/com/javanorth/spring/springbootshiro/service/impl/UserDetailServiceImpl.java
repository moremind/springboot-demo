package com.javanorth.spring.springbootshiro.service.impl;

import com.javanorth.spring.springbootshiro.constant.ShiroConstant;
import com.javanorth.spring.springbootshiro.dao.UserDetailDao;
import com.javanorth.spring.springbootshiro.entity.UserDetail;
import com.javanorth.spring.springbootshiro.exception.ExceptionEnum;
import com.javanorth.spring.springbootshiro.exception.UserAlreadyExistException;
import com.javanorth.spring.springbootshiro.exception.UserNotExistException;
import com.javanorth.spring.springbootshiro.service.UserDetailService;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.UUID;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    @Autowired
    UserDetailDao userDetailDao;

    @Override
    public boolean userRegister(String username, String password) {
        LogUtil.info(UserDetailServiceImpl.class, "user register username, pwd: {}, {}", username, password);
        // simple check user
        if (checkUserExist(username) != null) {
            throw new UserAlreadyExistException(ExceptionEnum.USER_EXIST.getRetCode(), ExceptionEnum.USER_EXIST.getMessage());
        } else {
            // generate uid by randomUUID
            String uuid = UUID.randomUUID().toString().replace("-", "");

            // generate salt
            String salt = uuid.substring(ShiroConstant.SALT_BEGIN, ShiroConstant.SALT_END);

            String md5Pwd = String.valueOf(new SimpleHash(ShiroConstant.ENCRYPT_TYPE, password,
                    salt, ShiroConstant.ENCRYPT_TIMES));
            UserDetail userDetail = UserDetail.builder().uid(uuid)
                    .username(username)
                    .salt(salt)
                    .password(md5Pwd)
                    .build();
            // insert user
            return userDetailDao.insertIntoUserDetail(userDetail);
        }
    }

    @Override
    public void userLogin(String username, String password) {
        LogUtil.info(UserDetailServiceImpl.class, "user register username, pwd: {}, {}", username, password);
        UserDetail userDetail = checkUserExist(username);
        if (userDetail == null) {
            throw new UserNotExistException(ExceptionEnum.USER_NOT_EXIST.getRetCode(),
                    ExceptionEnum.USER_NOT_EXIST.getMessage());
        } else {
            String md5Pwd = String.valueOf(new SimpleHash(ShiroConstant.ENCRYPT_TYPE, password,
                    userDetail.getSalt(), ShiroConstant.ENCRYPT_TIMES));
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, md5Pwd);
            subject.login(token);
        }
    }

    private UserDetail checkUserExist(String username) {
        return userDetailDao.selectUserDetailByUsername(username);
    }
}
