package cn.moremind.spring.springbootinit.controller;

import cn.moremind.spring.springbootinit.entity.Person;
import cn.moremind.spring.springbootinit.response.ResponseUtil;
import cn.moremind.spring.springbootinit.util.LogUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/web")
public class WebController {

    @GetMapping("/test")
    public ResponseUtil test(HttpServletRequest request) {
        LogUtil.info(WebController.class, Thread.currentThread().getName());
        Person person = Person.builder().name("test")
                .age(11)
                .address("test")
                .build();
        return ResponseUtil.success(request.getRequestURI());
    }
}
