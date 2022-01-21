package cn.moremind.spring.springbootinit.config;

import cn.moremind.spring.springbootinit.interceptor.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    /**
     * 自定义拦截器配置
     * @param registry 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webInterceptor());
    }

    @Bean
    public WebInterceptor webInterceptor() {
        return new WebInterceptor();
    }
}