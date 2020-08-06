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
        String username = (String) authenticationToken.getPrincipal();
        LogUtil.info(CustomRealm.class, "user name: {}", username);
        LogUtil.info(CustomRealm.class, "user pwd: {}", authenticationToken.getCredentials());
        UserDetail userDetail = userDetailDao.selectUserDetailByUsername(username);
        if (userDetail == null) {
            throw new UserNotExistException(ExceptionEnum.USER_NOT_EXIST.getRetCode(),
                    ExceptionEnum.USER_NOT_EXIST.getMessage());
        } else {

            return new SimpleAuthenticationInfo(username, "123456", ByteSource.Util.bytes(userDetail.getSalt()), getName());
        }
    }

    /**
     * set user's authority
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDetail user = (UserDetail) principalCollection.getPrimaryPrincipal();
        UserDetail userDetail = userDetailDao.selectUserDetailByUsername(user.getUsername());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(userRoleDao.selectUserRoleByUid(userDetail.getUid()));
        return simpleAuthorizationInfo;
    }


}
