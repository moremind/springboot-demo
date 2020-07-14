package com.javanorth.spring.springbootsecurity.dao;

import com.javanorth.spring.springbootsecurity.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserRoleDao {
    List<Role> selectRolesByUserId(String uid);
}
