package cn.moremind.spring.springbootredis.exampletwo.controller;

import cn.moremind.spring.springbootredis.exampletwo.response.ResultUtils;
import cn.moremind.spring.springbootredis.exampletwo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping
    public ResultUtils createToken() {
        return ResultUtils.success(tokenService.createToken());
    }
}
