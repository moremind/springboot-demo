package cn.moremind.spring.springbootsecurity.dao;

import cn.moremind.spring.springbootsecurity.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDetailDao {
    UserDetail selectUserByUsername(String username);
}
