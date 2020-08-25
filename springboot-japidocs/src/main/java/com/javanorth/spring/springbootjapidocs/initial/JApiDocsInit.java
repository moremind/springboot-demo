package com.javanorth.spring.springbootjapidocs.initial;

import com.javanorth.spring.springbootjapidocs.pojo.JApiDocsConfig;
import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;

public class JApiDocsInit {

    public static void initJApiDocs() {
        DocsConfig config = new DocsConfig();

        config.setProjectPath(JApiDocsConfig.projectPath); // 项目根目录
        config.setProjectName(JApiDocsConfig.projectName); // 项目名称
        config.setApiVersion(JApiDocsConfig.apiVersion);       // 声明该API的版本
        config.setDocsPath(JApiDocsConfig.docsPath); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.parseBoolean(JApiDocsConfig.autoGenerate));  // 配置自动生成
        if (Boolean.parseBoolean(JApiDocsConfig.generateMarkdown)) {
            config.addPlugin(new MarkdownDocPlugin());
        }
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
