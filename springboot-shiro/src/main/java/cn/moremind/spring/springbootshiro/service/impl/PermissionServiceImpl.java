package cn.moremind.spring.springbootshiro.service.impl;

import cn.moremind.spring.springbootshiro.dao.UserPermissionDao;
import cn.moremind.spring.springbootshiro.entity.Permission;
import cn.moremind.spring.springbootshiro.senum.PermissionType;
import cn.moremind.spring.springbootshiro.service.PermissionService;
import cn.moremind.spring.springbootshiro.util.LogUtil;
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
