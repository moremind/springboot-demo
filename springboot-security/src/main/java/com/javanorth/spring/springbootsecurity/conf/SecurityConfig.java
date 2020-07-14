package com.javanorth.spring.springbootsecurity.conf;

import com.javanorth.spring.springbootsecurity.filter.JwtTokenFilter;
import com.javanorth.spring.springbootsecurity.service.impl.UserDetailsServiceImpl;
import com.javanorth.spring.springbootsecurity.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //校验用户
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                LogUtil.info(this.getClass(), "password encode: {}", charSequence.toString());
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }
            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                LogUtil.info(this.getClass(), "psss {}", encode);
                boolean res = s.equals(encode);
                return res;
            }
        } );

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //因为使用JWT，所以不需要HttpSession
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //登录接口放行
                .antMatchers("/user/login", "/test").permitAll()
                //其他接口全部接受验证
                .anyRequest().authenticated();
        http.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }

//    @Bean
//    public JwtTokenFilter jwtFilter() {
//        return new JwtTokenFilter();
//    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
