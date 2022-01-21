package cn.moremind.spring.springbootsecurity.filter;

import cn.moremind.spring.springbootsecurity.service.impl.UserDetailsServiceImpl;
import com.google.gson.Gson;
import cn.moremind.spring.springbootsecurity.service.impl.TokenServiceImpl;
import cn.moremind.spring.springbootsecurity.util.LogUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static JwtTokenFilter jwtTokenFilter;

    /**
     * 存放Token的Header Key
     */
    private static final String HEADER_STRING = "Authorization";

    @Autowired
    TokenServiceImpl tokenService;


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    public void init() {
        jwtTokenFilter = this;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Gson gson = new Gson();
        // 校验jwt
        String header = httpServletRequest.getHeader(HEADER_STRING);
        LogUtil.info(this.getClass(), "current header: {}", header);

        // 判空
        boolean optional = Optional.ofNullable(header).isPresent();

        if (optional) {
            // 1.header不为空则解析header中的username
            Claims claims = jwtTokenFilter.tokenService.parseToken(header);
            String username = jwtTokenFilter.tokenService.getUsernameFromToken(claims);
            // 2.通过查询出来的username对该用户判断是否首授权访问
            if (Optional.ofNullable(username).isPresent() &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = jwtTokenFilter.userDetailsService.loadUserByUsername(username);
                LogUtil.info(this.getClass(), "user details: {}", userDetails.toString());

                // 3.如果未授权则进行授权
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                        httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            // 如果用户存在并且该接口未授权，则对该接口授权
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
