package com.javanorth.spring.springbootdruid.controller;

import com.javanorth.spring.springbootdruid.dao.UserDetailDao;
import com.javanorth.spring.springbootdruid.entity.Person;
import com.javanorth.spring.springbootdruid.response.ResponseUtil;
import com.javanorth.spring.springbootdruid.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/druidDatasource")
public class DruidController {
    @Autowired
    UserDetailDao userDetailDao;

    @PostMapping("/insert")
    public ResponseUtil insert(@RequestBody Person person, HttpServletRequest request) {
        //Person person = Person.builder().age(5).name("xiaoming").cardNumber("12345678").build();
        LogUtil.info(DruidController.class, "request params: {}", person);
        Person result = userDetailDao.save(person);
        return ResponseUtil.success(request.getRequestURI(), result);
    }

    @PostMapping("/delete")
    public ResponseUtil delete(HttpServletRequest request) {
        userDetailDao.deleteById("12345678");
        return ResponseUtil.success(request.getRequestURI());
    }

    @GetMapping("/list")
    public ResponseUtil listPerson(HttpServletRequest request) {
        List<Person> lists = userDetailDao.findAll();
        return ResponseUtil.success(request.getRequestURI(), lists);
    }
}
