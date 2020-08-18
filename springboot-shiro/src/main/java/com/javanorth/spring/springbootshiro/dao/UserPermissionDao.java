package com.javanorth.spring.springbootshiro.dao;

import com.javanorth.spring.springbootshiro.entity.Permission;
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
     * @see com.javanorth.spring.springbootshiro.senum.PermissionType
     * @param permissions
     */
    void insertAllPermission(List<Permission> permissions);

}
