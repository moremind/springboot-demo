package cn.moremind.spring.springbootshiro.dao;

import cn.moremind.spring.springbootshiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserRoleDao {

    /**
     * delete default role
     */
    void deleteAllUserRole();

    /**
     * init role
     * @param roles
     */
    void insertAllUserRole(List<Role> roles);

}
