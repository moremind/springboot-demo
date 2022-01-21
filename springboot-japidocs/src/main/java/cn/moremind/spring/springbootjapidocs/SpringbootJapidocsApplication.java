package cn.moremind.spring.springbootjapidocs;

import cn.moremind.spring.springbootjapidocs.initial.JApiDocsInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJapidocsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJapidocsApplication.class, args);
        JApiDocsInit.initJApiDocs();
    }

}
