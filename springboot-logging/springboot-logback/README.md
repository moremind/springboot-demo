# springboog整合篇-springboot整合logback
## Logback简介

logback是继log4j后的又一个日志框架，logback是springboot自带的日志框架，logback以严格的日志级别区分不同级别的日志（其他日志都是继承上一级的日志级别，例如：log4j2，log4j都是继承更高级别的日志），logback分为三个模块，logback-core，logback-classic，logback-access。

* logback-core：logback-core是logback的核心模块，是logback-classic和的logback-access的基础。
* logback-classic：实现了 slf4j API，配合 slf4j 使用，可以方便的切换其他日志框架。
* logback-access：与Servlet容器（如Tomcat和Jetty）集成，提供了 HTTP 访问日志接口。

## Springboot集成logback

之前说过，由于springboot自身是集成了logback，所以，在集成logback时只要springboot的自身依赖即可。

### pom依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

### application.properties文件的配置

```properties
logging.config=classpath:logback-spring.xml
```

### logback-spring.xml文件配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds">
    <property name="LOG_HOME" value="./WebAppLogs/logs"/>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
        </encoder>
    </appender>

    <!-- trace日志 -->
    <appender name="RollingFileTrace" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <file>${LOG_HOME}/trace.log</file>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!-- 只接受trace级别的日志，因为logback有严格的日志区分，所以某一个分类的日志只包括该类型的日志 -->
            <level>TRACE</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/trace_%d{yyyy-MM-dd-HH}-%i.log.zip</fileNamePattern>
            <!-- 单个文件大小 -->
            <maxFileSize>10 MB</maxFileSize>
            <!--fileNamePattern的格式为yyyy-MM-dd-HH,则日志回收时间也应该与fileNamePattern相同 -->
            <maxHistory>168</maxHistory>
            <!--总得文件日志-->
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
            <!--设置日志编码为utf-8-->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- debug日志 -->
    <appender name="RollingFileDebug" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <file>${LOG_HOME}/debug.log</file>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>DEBUG</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/debug_%d{yyyy-MM-dd-HH}-%i.log.zip</fileNamePattern>
            <!-- 单个文件大小 -->
            <maxFileSize>10 MB</maxFileSize>
            <!--fileNamePattern的格式为yyyy-MM-dd-HH,则日志回收时间也应该与fileNamePattern相同 -->
            <maxHistory>168</maxHistory>
            <!--总得文件日志-->
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
            <!--设置日志编码为utf-8-->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- info日志 -->
    <appender name="RollingFileInfo" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <file>${LOG_HOME}/info.log</file>
            <!-- 只要info的日志的配置 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/info_%d{yyyy-MM-dd-HH}-%i.log.zip</fileNamePattern>
            <!-- 单个文件大小 -->
            <maxFileSize>10 MB</maxFileSize>
            <!--fileNamePattern的格式为yyyy-MM-dd-HH,则日志回收时间也应该与fileNamePattern相同 -->
            <maxHistory>168</maxHistory>
            <!--总得文件日志-->
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
            <!--设置日志编码为utf-8-->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- warn日志 -->
    <appender name="RollingFileWarn" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <file>${LOG_HOME}/warn.log</file>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>WARN</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/warn_%d{yyyy-MM-dd-HH}-%i.log.zip</fileNamePattern>
            <!-- 单个文件大小 -->
            <maxFileSize>10 MB</maxFileSize>
            <!--fileNamePattern的格式为yyyy-MM-dd-HH,则日志回收时间也应该与fileNamePattern相同 -->
            <maxHistory>168</maxHistory>
            <!--总得文件日志-->
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
            <!--设置日志编码为utf-8-->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- error日志 -->
    <appender name="RollingFileError" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <file>${LOG_HOME}/error.log</file>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/error_%d{yyyy-MM-dd-HH}-%i.log.zip</fileNamePattern>
            <!-- 单个文件大小 -->
            <maxFileSize>10 MB</maxFileSize>
            <!--fileNamePattern的格式为yyyy-MM-dd-HH,则日志回收时间也应该与fileNamePattern相同 -->
            <maxHistory>168</maxHistory>
            <!--总得文件日志-->
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
            <!--设置日志编码为utf-8-->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- app应用日志 -->
    <appender name="RollingFileApp" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <file>${LOG_HOME}/app.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/app_%d{yyyy-MM-dd-HH}-%i.log.zip</fileNamePattern>
            <!-- 单个文件大小 -->
            <maxFileSize>10 MB</maxFileSize>
            <!--fileNamePattern的格式为yyyy-MM-dd-HH,则日志回收时间也应该与fileNamePattern相同 -->
            <maxHistory>168</maxHistory>
            <!--总得文件日志-->
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
            <!--设置日志编码为utf-8-->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <logger name="org.springframework" level="info"/>
    <!--使用日志继承属性 addtivity="false" -->
    <logger name="com.javanorth" level="all" addtivity="true"/>

    <root level="info">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="RollingFileTrace"/>
        <appender-ref ref="RollingFileDebug"/>
        <appender-ref ref="RollingFileInfo"/>
        <appender-ref ref="RollingFileWarn"/>
        <appender-ref ref="RollingFileError"/>
        <appender-ref ref="RollingFileApp"/>
    </root>
</configuration>
```

### 将所有日志写入一个日志文件的配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds">
    <property name="LOG_HOME" value="./WebAppLogs/logs"/>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
        </encoder>
    </appender>

    <!-- app应用日志 -->
    <appender name="RollingFileApp" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <append>true</append>
        <file>${LOG_HOME}/app.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${LOG_HOME}/app_%d{yyyy-MM-dd-HH}-%i.log.zip</fileNamePattern>
            <!-- 单个文件大小 -->
            <maxFileSize>10 MB</maxFileSize>
            <!--fileNamePattern的格式为yyyy-MM-dd-HH,则日志回收时间也应该与fileNamePattern相同 -->
            <maxHistory>168</maxHistory>
            <!--总得文件日志-->
            <totalSizeCap>10GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%t表示线程名，%msg：日志消息，%n是换行符-->
            <pattern>[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n</pattern>
            <!--设置日志编码为utf-8-->
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <logger name="org.springframework" level="info"/>
    <!--使用日志继承属性 addtivity="false" -->
    <logger name="com.javanorth" level="all" addtivity="true"/>

    <root level="info">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="RollingFileApp"/>
    </root>
</configuration>
```

## 通过slf4j实现logback日志

```java
public class LogUtil {

    /**
     * debug级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void debug(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.debug(msg, params);
    }

    /**
     * trace日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void trace(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.trace(msg, params);
    }

    /**
     * info级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void info(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz.getName());
        logger.info(msg, params);
    }

    /**
     * warn级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void warn(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.warn(msg, params);
    }

    /**
     * error级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void error(Class clazz, String msg, Object... params) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(msg, params);
    }
}
```

## slf4j实现的调用

```java
void contextLoads() {
    LogUtil.info(this.getClass(), "this is info log");
}
```

## 日志输出

![logback](https://javanorth-1251602255.cos.ap-chengdu.myqcloud.com/img/logback.jpg)

## 总结

总体来说，logback是在一定程度上是比log4j2性能上差一点的，这也在我们之前的文章分析过，但是logback仍然为很多人所喜爱，个人认为有logback配置的确也比log4j2简单和logback的日志更加简化，而且对日志继承性要求很严格，这在一定的程度上方便问题查找以及减少不必要的日志。

## 参考文章

* [1].logback的使用和logback.xml详解.https://www.cnblogs.com/warking/p/5710303.html.
* [2].logback网站.http://logback.qos.ch/documentation.html.

