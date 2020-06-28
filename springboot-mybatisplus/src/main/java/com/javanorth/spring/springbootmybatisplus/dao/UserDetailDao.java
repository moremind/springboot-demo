package com.javanorth.spring.springbootmybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javanorth.spring.springbootmybatisplus.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserDetailDao extends BaseMapper<UserDetail> {
}
