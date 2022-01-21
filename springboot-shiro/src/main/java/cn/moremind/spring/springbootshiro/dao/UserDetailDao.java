package cn.moremind.spring.springbootshiro.dao;

import cn.moremind.spring.springbootshiro.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * query role by uid
     * @param uid
     * @return
     */
    List<String> selectUserRoleByUid(String uid);

    /**
     * query user permission by uid
     * @param uid
     * @return
     */
    List<String> selectUserPermissionByUic(String uid);
}
