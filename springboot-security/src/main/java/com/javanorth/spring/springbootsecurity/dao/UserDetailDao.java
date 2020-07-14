package com.javanorth.spring.springbootsecurity.dao;

import com.javanorth.spring.springbootsecurity.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDetailDao {
    UserDetail selectUserByUsername(String username);
}
