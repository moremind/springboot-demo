package cn.moremind.spring.springbootjapidocs.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties", encoding = "utf-8")
public class JApiDocsConfig {
    public static String projectPath;
    public static String projectName;
    public static String apiVersion;
    public static String docsPath;
    public static String autoGenerate;
    public static String generateMarkdown;

    public static String getProjectPath() {
        return projectPath;
    }

    @Value("${japi.project-path}")
    public void setProjectPath(String projectPath) {
        JApiDocsConfig.projectPath = projectPath;
    }

    public static String getProjectName() {
        return projectName;
    }

    @Value("${japi.project-name}")
    public void setProjectName(String projectName) {
        JApiDocsConfig.projectName = projectName;
    }

    public static String getApiVersion() {
        return apiVersion;
    }

    @Value("${japi.api-version}")
    public void setApiVersion(String apiVersion) {
        JApiDocsConfig.apiVersion = apiVersion;
    }

    public static String getDocsPath() {
        return docsPath;
    }

    @Value("${japi.docs-path}")
    public void setDocsPath(String docsPath) {
        JApiDocsConfig.docsPath = docsPath;
    }

    public static String getAutoGenerate() {
        return autoGenerate;
    }

    @Value("${japi.auto-generate}")
    public void setAutoGenerate(String autoGenerate) {
        JApiDocsConfig.autoGenerate = autoGenerate;
    }

    public static String getGenerateMarkdown() {
        return generateMarkdown;
    }

    @Value("${japi.generate-markdown}")
    public void setGenerateMarkdown(String generateMarkdown) {
        JApiDocsConfig.generateMarkdown = generateMarkdown;
    }
}
