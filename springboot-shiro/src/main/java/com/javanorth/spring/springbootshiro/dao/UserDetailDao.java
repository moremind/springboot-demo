package com.javanorth.spring.springbootshiro.dao;

import com.javanorth.spring.springbootshiro.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDetailDao {

    /**
     * query user into
     * @param username username
     * @return user detail
     */
    UserDetail selectUserDetailByUsername(String username);

    /**
     * insert user information
     * @param userDetail
     * @return
     */
    boolean insertIntoUserDetail(UserDetail userDetail);
}
