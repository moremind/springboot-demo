package com.javanorth.spring.springbootmultidatasource.controller;

import com.javanorth.spring.springbootmultidatasource.dao.primary.PrimaryDao;
import com.javanorth.spring.springbootmultidatasource.dao.secondary.SecondaryDao;
import com.javanorth.spring.springbootmultidatasource.entity.Person;
import com.javanorth.spring.springbootmultidatasource.response.ResponseUtil;
import com.javanorth.spring.springbootmultidatasource.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/multids")
public class MultiDataSourceController {


    @Autowired
    PrimaryDao primaryDao;

    @Autowired
    SecondaryDao secondaryDao;

    @GetMapping("/test")
    public String test() {
        LogUtil.info(this.getClass(), "test!");
        return "success";
    }

    @PostMapping("/insert")
    public ResponseUtil test1(HttpServletRequest request) {
        String path = request.getRequestURI();
        Person person = Person.builder().cardNumber("523426").name("666666").age(6).build();
        boolean primaryFlag = primaryDao.insert(person);
        boolean secondaryFlag = secondaryDao.insert2(person);
        return primaryFlag && secondaryFlag ? ResponseUtil.success(path) : ResponseUtil.error(path);
    }

}
