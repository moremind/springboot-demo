package com.javanorth.spring.springbootshiro.service.impl;

import com.javanorth.spring.springbootshiro.dao.UserRoleDao;
import com.javanorth.spring.springbootshiro.entity.Role;
import com.javanorth.spring.springbootshiro.handler.InitExecutorHandler;
import com.javanorth.spring.springbootshiro.senum.AdminType;
import com.javanorth.spring.springbootshiro.service.RoleService;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public void initRoleDatabase() {
        LogUtil.info(RoleServiceImpl.class, "delete role");
        userRoleDao.deleteAllUserRole();
        List<Role> roles = new ArrayList<>();
        for (AdminType adminType : AdminType.values()) {
            roles.add(new Role(adminType.getId(), adminType.getType()));
        }
        LogUtil.info(InitExecutorHandler.class, "all roles: {}", roles);
        LogUtil.info(RoleServiceImpl.class, "add role");
        userRoleDao.insertAllUserRole(roles);
    }
}
