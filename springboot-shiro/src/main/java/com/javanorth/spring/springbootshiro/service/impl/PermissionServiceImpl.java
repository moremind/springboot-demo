package com.javanorth.spring.springbootshiro.service.impl;

import com.javanorth.spring.springbootshiro.dao.UserPermissionDao;
import com.javanorth.spring.springbootshiro.entity.Permission;
import com.javanorth.spring.springbootshiro.senum.PermissionType;
import com.javanorth.spring.springbootshiro.service.PermissionService;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    UserPermissionDao userPermissionDao;

    @Override
    public void initPermissionDatabase() {
        LogUtil.info(PermissionServiceImpl.class, "delete default permission");
        userPermissionDao.deleteAllPermission();
        List<Permission> permissions = new ArrayList<>();

        for (PermissionType permission: PermissionType.values()) {
            permissions.add(new Permission(permission.getId(), permission.getName()));
        }
        LogUtil.info(PermissionServiceImpl.class, "init default permission");
        userPermissionDao.insertAllPermission(permissions);
    }
}
