package cn.moremind.spring.springbootredis.exampletwo.service.impl;


import cn.moremind.spring.springbootredis.exampletwo.exception.ExceptionEnum;
import cn.moremind.spring.springbootredis.exampletwo.exception.ServiceException;
import cn.moremind.spring.springbootredis.exampletwo.service.TokenService;
import cn.moremind.spring.springbootredis.utils.LogUtil;
import cn.moremind.spring.springbootredis.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_PREFIX = "token_";
    private static final String TOKEN_NAME = "token";

    @Autowired
    RedisUtils redisUtils;
    /**;
     * 创建接口请求的token
     * @return 返回接口接口请求需要的token
     */
    @Override
    public String createToken() {
        // 使用uuid或者其他生成唯一id的方式生产一个token
        String token = TOKEN_PREFIX.concat(UUID.randomUUID().toString().replace("-", ""));
        LogUtil.info(getClass(), "current token is:", token);
        // 通过redis设置token过期时间
        redisUtils.setEx(token, token, 50000L, TimeUnit.MILLISECONDS);
        return token;
    }

    /**
     * 核验接口的token
     */
    @Override
    public void checkToken(HttpServletRequest request) {
        LogUtil.info(this.getClass(), "this request is:", request.toString());
        String token = request.getHeader(TOKEN_NAME);
        // 判断接口请求头是否包括token
        if (StringUtils.isBlank(token)) {
            throw new ServiceException(ExceptionEnum.HEADER_NO_TOKEN.getCode(), ExceptionEnum.HEADER_NO_TOKEN.getMsg());
        }
        // 判断redis是否存在该key
        if (!redisUtils.hasKey(token)) {
            throw new ServiceException(ExceptionEnum.TOKEN_NOT_EXIST.getCode(), ExceptionEnum.TOKEN_NOT_EXIST.getMsg());
        }
        boolean remove = redisUtils.delete(token);
        if (!remove) {
            throw new ServiceException(ExceptionEnum.DELETE_KEY_FAILED.getCode(), ExceptionEnum.DELETE_KEY_FAILED.getMsg());
        }

        LogUtil.info(this.getClass(), "delete key from redis:", token);
    }
}
