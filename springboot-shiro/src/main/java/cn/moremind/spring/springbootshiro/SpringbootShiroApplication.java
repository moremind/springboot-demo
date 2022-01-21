package cn.moremind.spring.springbootshiro;

import cn.moremind.spring.springbootshiro.handler.InitExecutorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroApplication.class, args);

        // init role db
        InitExecutorHandler.initRoleDatabase();
    }

}
