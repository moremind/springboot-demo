package com.javanorth.spring.springbootjapidocs;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJapidocsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJapidocsApplication.class, args);
        DocsConfig config = new DocsConfig();

        config.setProjectPath("./springboot-japidocs"); // 项目根目录
        config.setProjectName("springboot-japidocs"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("./springboot-japidocs/docs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }

}
