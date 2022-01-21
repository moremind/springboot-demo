package cn.moremind.spring.springbootmultidatasource.controller;

import cn.moremind.spring.springbootmultidatasource.dao.secondary.SecondaryDao;
import cn.moremind.spring.springbootmultidatasource.response.ResponseUtil;
import cn.moremind.spring.springbootmultidatasource.dao.primary.PrimaryDao;
import cn.moremind.spring.springbootmultidatasource.entity.Person;
import cn.moremind.spring.springbootmultidatasource.util.LogUtil;
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
