package com.javanorth.spring.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javanorth.spring.mybatisplus.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserDetailDao extends BaseMapper<UserDetail> {
}
