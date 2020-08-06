package com.javanorth.spring.springbootshiro.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserRoleDao {

    List<String> selectUserRoleByUid(String uid);
}
