package com.javanorth.spring.springbootjapidocs;

import com.javanorth.spring.springbootjapidocs.initial.JApiDocsInit;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJapidocsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJapidocsApplication.class, args);
        JApiDocsInit.initJApiDocs();
    }

}
