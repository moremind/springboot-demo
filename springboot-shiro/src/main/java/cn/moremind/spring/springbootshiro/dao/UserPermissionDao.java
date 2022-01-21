package cn.moremind.spring.springbootshiro.dao;

import cn.moremind.spring.springbootshiro.entity.Permission;
import cn.moremind.spring.springbootshiro.senum.PermissionType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserPermissionDao {

    /**
     * delete all permission
     */
    void deleteAllPermission();

    /**
     * insert all permission by enum of PermissionType
     * @see PermissionType
     * @param permissions
     */
    void insertAllPermission(List<Permission> permissions);

}
