package cn.moremind.spring.springbootshiro.handler;

import cn.moremind.spring.springbootshiro.service.impl.PermissionServiceImpl;
import cn.moremind.spring.springbootshiro.service.impl.RoleServiceImpl;
import cn.moremind.spring.springbootshiro.util.LogUtil;
import cn.moremind.spring.springbootshiro.util.SpringUtils;

/**
 * init exec
 */
public class InitExecutorHandler {
    private static RoleServiceImpl roleService = SpringUtils.getBean(RoleServiceImpl.class);
    private static PermissionServiceImpl permissionService = SpringUtils.getBean(PermissionServiceImpl.class);

    public static void initRoleDatabase() {
        LogUtil.info(InitExecutorHandler.class, "====== Start Init tb_role and tb_permission ======");
        roleService.initRoleDatabase();
        permissionService.initPermissionDatabase();
    }
}
