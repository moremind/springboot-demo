package cn.moremind.spring.springbootsecurity.service.impl;

import cn.moremind.spring.springbootsecurity.dao.UserDetailDao;
import cn.moremind.spring.springbootsecurity.dao.UserRoleDao;
import cn.moremind.spring.springbootsecurity.entity.UserDetail;
import cn.moremind.spring.springbootsecurity.exception.ExceptionEnum;
import cn.moremind.spring.springbootsecurity.exception.ServiceException;
import cn.moremind.spring.springbootsecurity.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cn.moremind.spring.springbootsecurity.entity.Role;

import java.util.List;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailDao userDetailDao;

    @Autowired
    UserRoleDao userRoleDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        roleList.add(Role.builder().id(0).name("guest").build());
        UserDetail userDetail = userDetailDao.selectUserByUsername(username);
        Optional.ofNullable(userDetail).orElseThrow(() -> new ServiceException(ExceptionEnum.NULL_OF_USER_DETAIL.getRetCode(),
                 ExceptionEnum.NULL_OF_USER_DETAIL.getMessage()));
        List<Role> roles = userRoleDao.selectRolesByUserId(userDetail.getUid());
        LogUtil.info(this.getClass(), "user roles: {}", roles.toString());
        userDetail.setAuthorities(roles);
        return userDetail;
    }

}
