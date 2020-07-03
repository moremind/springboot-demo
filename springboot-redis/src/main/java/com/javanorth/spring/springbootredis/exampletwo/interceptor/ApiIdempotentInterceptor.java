package com.javanorth.spring.springbootredis.exampletwo.interceptor;

import com.google.gson.Gson;
import com.javanorth.spring.springbootredis.exampletwo.annotation.ApiIdempotent;
import com.javanorth.spring.springbootredis.exampletwo.exception.ServiceException;
import com.javanorth.spring.springbootredis.exampletwo.response.ResultUtils;
import com.javanorth.spring.springbootredis.exampletwo.service.TokenService;
import com.javanorth.spring.springbootredis.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class ApiIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 拿到请求对应的方法
        Method method = handlerMethod.getMethod();

        LogUtil.info(getClass(), "this method name is:", method.getName());

        // 获取ApiI
        ApiIdempotent apiIdempotent = method.getAnnotation(ApiIdempotent.class);

        Gson gson = new Gson();

        if (apiIdempotent != null) {
            try {
                tokenService.checkToken(request);
                return true;
            } catch (ServiceException e) {
                LogUtil.error(this.getClass(), e.getMessage());
                // 将对饮的错误返回
                response.getWriter().write(gson.toJson(ResultUtils.error(e.getRetCode(), e.getMsg())));
                return false;
            } catch (Exception ex) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
