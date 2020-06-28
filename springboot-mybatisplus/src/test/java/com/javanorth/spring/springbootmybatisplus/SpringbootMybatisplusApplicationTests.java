package com.javanorth.spring.springbootmybatisplus;

import com.javanorth.spring.springbootmybatisplus.dao.UserDetailDao;
import com.javanorth.spring.springbootmybatisplus.entity.UserDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserDetailDao userDetailDao;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserDetail> userList = userDetailDao.selectList(null);
        userList.forEach(System.out::println);
    }

}
