package cn.moremind.spring.springbootsecurity.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;


public interface TokenService {

    /**
     * 生成token
     * @param userDetails 用户信息
     * @return token
     */
    String generateToken(UserDetails userDetails);

    /**
     * 解析token
     * @param token token
     * @return 解析后的body
     */
    Claims parseToken(String token);

    /**
     * 获取token的中username
     * @param claims
     * @return
     */
    String getUsernameFromToken(Claims claims);

}
