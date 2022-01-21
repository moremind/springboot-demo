package cn.moremind.spring.springbootshiro;

import cn.moremind.spring.springbootshiro.dao.UserRoleDao;
import cn.moremind.spring.springbootshiro.entity.Role;
import cn.moremind.spring.springbootshiro.handler.InitExecutorHandler;
import cn.moremind.spring.springbootshiro.senum.AdminType;
import cn.moremind.spring.springbootshiro.util.LogUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserRoleDao userRoleDao;

    @Test
    public void initRoleDatabase() {
        List<Role> roles = new ArrayList<>();
        AdminType[] adminTypes = AdminType.values();
        LogUtil.info(InitExecutorHandler.class, "all roles: {}");
        for (AdminType adminType : adminTypes) {
            roles.add(new Role(adminType.getId(), adminType.getType()));
        }
        LogUtil.info(InitExecutorHandler.class, "all roles: {}", roles);
        userRoleDao.insertAllUserRole(roles);
    }





























    @Test
    void test2() {
        String name = null;
        System.out.println(StringUtils.isEmpty(name));
    }

}
