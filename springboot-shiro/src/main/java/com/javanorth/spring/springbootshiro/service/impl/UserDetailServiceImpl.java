package com.javanorth.spring.springbootshiro.service.impl;

import com.javanorth.spring.springbootshiro.dao.UserDetailDao;
import com.javanorth.spring.springbootshiro.entity.UserDetail;
import com.javanorth.spring.springbootshiro.exception.ExceptionEnum;
import com.javanorth.spring.springbootshiro.exception.PasswordErrorException;
import com.javanorth.spring.springbootshiro.exception.UserAlreadyExistException;
import com.javanorth.spring.springbootshiro.exception.UserNotExistException;
import com.javanorth.spring.springbootshiro.service.UserDetailService;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            String salt = uuid.substring(0, 5);

            String md5Pwd = new SimpleHash("MD5", password, salt, 1).toString();
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
    public boolean userLogin(String username, String password) {
        LogUtil.info(UserDetailServiceImpl.class, "user register username, pwd: {}, {}", username, password);
        UserDetail userDetail = checkUserExist(username);
        if (userDetail == null) {
            throw new UserNotExistException(ExceptionEnum.USER_NOT_EXIST.getRetCode(),
                    ExceptionEnum.USER_NOT_EXIST.getMessage());
        } else {
            String md5Pwd = new SimpleHash("MD5", password, userDetail.getSalt(), 1).toString();
            if (md5Pwd.equals(userDetail.getPassword())) {
                LogUtil.info(UserDetailServiceImpl.class, "user login success");
                // login success
                return true;
            } else {
                throw new PasswordErrorException(ExceptionEnum.PWD_ERROR.getRetCode(), ExceptionEnum.PWD_ERROR.getMessage());
            }
        }
    }

    private UserDetail checkUserExist(String username) {
        return userDetailDao.selectUserDetailByUsername(username);
    }
}
