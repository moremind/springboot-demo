package cn.moremind.spring.springbootsecurity.controller;

import cn.moremind.spring.springbootsecurity.response.ResponseUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class JsonWebTokenController {

    /**
     * 测试接口
     * @param request 默认的request
     * @return 默认响应
     */
    @GetMapping("/test")
    public ResponseUtil test(HttpServletRequest request) {
        return ResponseUtil.success(request.getRequestURI());
    }

}
