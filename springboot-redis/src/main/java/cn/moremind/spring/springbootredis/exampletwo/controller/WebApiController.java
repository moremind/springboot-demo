package cn.moremind.spring.springbootredis.exampletwo.controller;

import cn.moremind.spring.springbootredis.exampletwo.annotation.ApiIdempotent;
import cn.moremind.spring.springbootredis.exampletwo.response.ResultUtils;
import cn.moremind.spring.springbootredis.exampletwo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebApiController {
    @Autowired
    TokenService tokenService;


    @ApiIdempotent
    @GetMapping
    public ResultUtils test() {
        return ResultUtils.success();
    }
}
