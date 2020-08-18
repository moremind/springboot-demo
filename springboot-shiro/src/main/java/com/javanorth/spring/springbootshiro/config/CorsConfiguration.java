package com.javanorth.spring.springbootshiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * 跨域请求访问控制
     * @param registry 跨域请求参数
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS")
                .allowedOrigins("*")
                .allowCredentials(true)
                .maxAge(1800L);
    }

}