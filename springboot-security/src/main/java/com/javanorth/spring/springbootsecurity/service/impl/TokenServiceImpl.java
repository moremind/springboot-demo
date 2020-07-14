package com.javanorth.spring.springbootsecurity.service.impl;

import com.javanorth.spring.springbootsecurity.entity.UserDetail;
import com.javanorth.spring.springbootsecurity.service.TokenService;
import com.javanorth.spring.springbootsecurity.util.LogUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService, Serializable {

    private static final String CLAIM_KEY_USERNAME = "username";
    private static final long EXPIRATION_TIME = 432000000;

    @Value("${jwtConfig.secret}")
    private String SECRET_KEY;




    /**
     * 生成token
     * 1.设置用户角色
     * 2.设置token过期时间
     * 3.设置token的签发时间
     * 3.设置token的加密方式
     * @param userDetails
     * @return
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims  = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                // 设置header
                .setHeader(header)
                // claim设置用户的角色
                .setClaims(claims)
                // 设置token过期时间
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                // 设置加密方式
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析token
     * @param token token字符串
     */
    @Override
    public Claims parseToken(String token) {
        LogUtil.info(this.getClass(), "parseToken params: {}", token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        LogUtil.info(this.getClass(),"claims: {}", claims.toString());
        return claims;
    }

    @Override
    public String getUsernameFromToken(Claims claims) {
        LogUtil.info(this.getClass(), "claims params is : {}", claims.toString());
        return String.valueOf(claims.get(CLAIM_KEY_USERNAME));
    }


    /**
     * 生成密钥
     * @return 生成的密钥
     */
    private Key generateKey() {
        String keys = Encoders.BASE64.encode(SECRET_KEY.getBytes());
        return Keys.hmacShaKeyFor(keys.getBytes());
    }


}
