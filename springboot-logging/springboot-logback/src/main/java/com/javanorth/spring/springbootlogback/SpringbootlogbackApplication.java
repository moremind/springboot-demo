package com.javanorth.spring.springbootlogback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootlogbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootlogbackApplication.class, args);
    }

}
