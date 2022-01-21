package cn.moremind.spring.springbootshiro.filter;

import cn.moremind.spring.springbootshiro.response.ResponseResult;
import cn.moremind.spring.springbootshiro.response.ResponseUtil;
import cn.moremind.spring.springbootshiro.util.LogUtil;
import com.google.gson.Gson;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class ShiroFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        LogUtil.info(ShiroFilter.class, "this api not auth");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(ResponseUtil.fail(ResponseResult.AUTH_FAILED.getCode(),
                ResponseResult.AUTH_FAILED.getMsg(), httpServletRequest.getRequestURI())));
        return false;
    }
}
