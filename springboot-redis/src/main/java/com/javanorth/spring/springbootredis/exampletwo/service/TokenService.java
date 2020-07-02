package com.javanorth.spring.springbootredis.exampletwo.service;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    /**
     * 创建接口请求的token
     * @return 返回接口接口请求需要的token
     */
    String createToken();

    /**
     * 核验接口的token
     */
    void checkToken(HttpServletRequest request);

}
