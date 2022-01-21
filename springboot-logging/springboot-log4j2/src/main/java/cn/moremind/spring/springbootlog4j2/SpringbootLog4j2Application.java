package cn.moremind.spring.springbootlog4j2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootLog4j2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootLog4j2Application.class, args);
    }

}
