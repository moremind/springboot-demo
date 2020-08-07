package com.javanorth.spring.springbootshiro.bean;

import com.javanorth.spring.springbootshiro.dao.UserDetailDao;
import com.javanorth.spring.springbootshiro.dao.UserRoleDao;
import com.javanorth.spring.springbootshiro.entity.UserDetail;
import com.javanorth.spring.springbootshiro.exception.ExceptionEnum;
import com.javanorth.spring.springbootshiro.exception.UserNotExistException;
import com.javanorth.spring.springbootshiro.util.LogUtil;
import com.javanorth.spring.springbootshiro.util.SpringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealm extends AuthorizingRealm {

    private UserDetailDao userDetailDao = SpringUtils.getBean(UserDetailDao.class);
    private UserRoleDao userRoleDao = SpringUtils.getBean(UserRoleDao.class);


    /**
     * auth user login info
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = String.valueOf(authenticationToken.getPrincipal());
        LogUtil.info(CustomRealm.class, "user name: {}", username);
        // query user information
        UserDetail userDetail = userDetailDao.selectUserDetailByUsername(username);
        return new SimpleAuthenticationInfo(username, userDetail.getPassword(), ByteSource.Util.bytes(userDetail.getSalt()), getName());
    }

    /**
     * set user's authority
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // get username
        String user = String.valueOf(principalCollection.getPrimaryPrincipal());
        // query user roles
        UserDetail userDetail = userDetailDao.selectUserDetailByUsername(user);
        LogUtil.info(CustomRealm.class, "user info: {}", userDetail.toString());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userRoleDao.selectUserRoleByUid(userDetail.getUid()));
        return simpleAuthorizationInfo;
    }


}
