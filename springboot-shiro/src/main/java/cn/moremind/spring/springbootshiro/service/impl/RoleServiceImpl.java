package cn.moremind.spring.springbootshiro.service.impl;

import cn.moremind.spring.springbootshiro.dao.UserRoleDao;
import cn.moremind.spring.springbootshiro.entity.Role;
import cn.moremind.spring.springbootshiro.handler.InitExecutorHandler;
import cn.moremind.spring.springbootshiro.senum.AdminType;
import cn.moremind.spring.springbootshiro.service.RoleService;
import cn.moremind.spring.springbootshiro.util.LogUtil;
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
