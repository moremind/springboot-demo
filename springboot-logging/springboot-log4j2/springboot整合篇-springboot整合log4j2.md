## log4j2简介

log4j2设计之初是为了审计，log4j2是log4j 1.x 的升级版，参考了logback的一些优秀的设计，并且修复了一些问题，因此带来了一些重大的提升。

* log4j2相比log4j和logback有很大的性能替身。
* log4j2能够自动重载配置，log4j2通过参数配置，可以不用重启应而自动重新加载log4j2的配置文件。
* log4j2能够避免死锁。

## Log4j2与其他框架比较

![](https://javanorth-1251602255.cos.ap-chengdu.myqcloud.com/img/async-throughput-comparison.png)

<center>异步日志比较</center>

![](https://javanorth-1251602255.cos.ap-chengdu.myqcloud.com/img/SyncThroughputLoggerComparisonLinux.png)

<center>同步日志比较</center>

明显可以看出，与各个日志框架对比而言，无论在同步或者异步情况下，log4j2表现更加优异，而其他日志就显得差强人意了。

## Springboot集成Log4j2

本文仅采用同步日志写法，异步日志暂不赘述。实际线上情况可能仅会打印info级别的日志，而info级别的日志会包括info-log、warn-log、error-log、fatal-log。

### pom依赖

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!-- 排除springboot自带的log依赖 -->
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.apache.logging.log4j</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- 添加springboot的log4j2依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
</dependencies>
```

### application.properties配置

```properties
logging.config=classpath:log4j2-spring.xml
```

### log4j2-spring.xml文件配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
<!--Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出-->
<!--monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数-->
<configuration status="WARN" monitorInterval="30">
    <!-- 配置日志文件输出目录，此配置将日志输出到tomcat根目录下的指定文件夹 -->
    <properties>
        <property name="LOG_HOME">./WebAppLogs/logs</property>
    </properties>
    <!--先定义所有的appender-->
    <appenders>
        <!-- 优先级从高到低分别是 OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL -->
        <!-- 单词解释： Match：匹配 DENY：拒绝 Mismatch：不匹配 ACCEPT：接受 -->
        <!-- DENY，日志将立即被抛弃不再经过其他过滤器； NEUTRAL，有序列表里的下个过滤器过接着处理日志； ACCEPT，日志会被立即处理，不再经过剩余过滤器。 -->
        <!--输出日志的格式
         %d{yyyy-MM-dd HH:mm:ss, SSS} : 日志生产时间
         %p : 日志输出格式
         %c : logger的名称
         %m : 日志内容，即 logger.info("message")
         %n : 换行符
         %C : Java类名
         %L : 日志输出所在行数
         %M : 日志输出所在方法名
         hostName : 本地机器名
         hostAddress : 本地ip地址 -->
        <!--这个输出控制台的配置-->
        <console name="Console" target="SYSTEM_OUT">
            <!--输出日志的格式-->
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{1.} - %m%n"/>
            <!--<PatternLayout pattern="[%d{HH:mm:ss:SSS}] - (%F:%l) - %m%n"/>-->
            <!--<PatternLayout pattern="[%d{HH:mm:ss:SSS}] (%F:%L) %m%n" />-->
        </console>
        <!-- 这个会打印出所有的info及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
        <!-- TRACE级别日志 ; 设置日志格式并配置日志压缩格式，压缩文件独立放在一个文件夹内， 日期格式不能为冒号，否则无法生成，因为文件名不允许有冒号，此appender只输出trace级别的数据到trace.log -->
        <RollingFile name="RollingFileTrace" immediateFlush="true" fileName="${LOG_HOME}/trace.log"
                     filePattern="${LOG_HOME}/$${date:yyyy-MM-dd}/trace_%d{yyyy-MM-dd-HH}-%i.log.zip">
            <ThresholdFilter level="trace" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
            <!-- DefaultRolloverStrategy属性如不设置，则默认为最多同一文件夹下7个文件，这里设置了20 -->
            <DefaultRolloverStrategy max="20">
                <!--这里的age必须和filePattern协调, 后者是精确到HH, 这里就要写成xH, xd就不起作用
                    另外, 数字最好>2, 否则可能造成删除的时候, 最近的文件还处于被占用状态,导致删除不成功!-->
                <Delete basePath="${LOG_HOME}/$${date:yyyy-MM-dd}/" maxDepth="2">
                    <IfFileName glob="trace_*.zip"/>
                    <!-- 保存时间与filePattern相同即可 -->
                    <!-- 如果filePattern为：yyyy-MM-dd-HH:mm:ss, age也可以为5s,表示日志存活时间为5s -->
                    <IfLastModified age="168H"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>

        <RollingFile name="RollingFileDebug" immediateFlush="true" fileName="${LOG_HOME}/debug.log"
                     filePattern="${LOG_HOME}/$${date:yyyy-MM-dd}/debug_%d{yyyy-MM-dd-HH}-%i.log.zip">
            <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
            <DefaultRolloverStrategy max="20">
                <Delete basePath="${LOG_HOME}/$${date:yyyy-MM-dd}/" maxDepth="2">
                    <IfFileName glob="debug_*.zip"/>
                    <IfLastModified age="168H"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>

        <!-- info日志配置 -->
        <RollingFile name="RollingFileInfo" immediateFlush="true" fileName="${LOG_HOME}/info.log"
                     filePattern="${LOG_HOME}/$${date:yyyy-MM-dd}/info_%d{yyyy-MM-dd-HH}-%i.log.zip">
            <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
            <DefaultRolloverStrategy max="20">
                <Delete basePath="${LOG_HOME}/$${date:yyyy-MM-dd}/" maxDepth="2">
                    <IfFileName glob="info_*.zip"/>
                    <IfLastModified age="168H"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>

        <!-- warn日志配置 -->
        <RollingFile name="RollingFileWarn" immediateFlush="true" fileName="${LOG_HOME}/warn.log"
                     filePattern="${LOG_HOME}/$${date:yyyy-MM-dd}/warn_%d{yyyy-MM-dd-HH}-%i.log.zip">
            <ThresholdFilter level="warn" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
            <DefaultRolloverStrategy max="20">
                <Delete basePath="${LOG_HOME}/$${date:yyyy-MM-dd}/" maxDepth="2">
                    <IfFileName glob="warn_*.zip"/>
                    <IfLastModified age="168H"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>

        <!-- error日志配置 -->
        <RollingFile name="RollingFileError" immediateFlush="true" fileName="${LOG_HOME}/error.log"
                     filePattern="${LOG_HOME}/$${date:yyyy-MM-dd}/error_%d{yyyy-MM-dd-HH}-%i.log.zip">
            <ThresholdFilter level="error" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
            <DefaultRolloverStrategy max="20">
                <Delete basePath="${LOG_HOME}/$${date:yyyy-MM-dd}/" maxDepth="2">
                    <IfFileName glob="error_*.zip"/>
                    <IfLastModified age="168H"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>
    </appenders>
    <!--然后定义logger，只有定义了logger并引入的appender，appender才会生效-->
    <loggers>
        <!--过滤掉spring和mybatis的一些无用的DEBUG信息-->
        <logger name="org.springframework" level="INFO"/>
        <logger name="org.mybatis" level="INFO"/>
        <root level="all">
            <appender-ref ref="Console"/>
            <appender-ref ref="RollingFileDebug"/>
            <appender-ref ref="RollingFileTrace"/>
            <appender-ref ref="RollingFileInfo"/>
            <appender-ref ref="RollingFileWarn"/>
            <appender-ref ref="RollingFileError"/>
        </root>
    </loggers>
</configuration>
```

### 仅info-log的log4j2-spring.xml的配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--日志级别以及优先级排序: OFF > FATAL > ERROR > WARN > INFO > DEBUG > TRACE > ALL -->
<!--Configuration后面的status，这个用于设置log4j2自身内部的信息输出，可以不设置，当设置成trace时，你会看到log4j2内部各种详细输出-->
<!--monitorInterval：Log4j能够自动检测修改配置 文件和重新配置本身，设置间隔秒数-->
<configuration status="WARN" monitorInterval="30">
    <!-- 配置日志文件输出目录，此配置将日志输出到tomcat根目录下的指定文件夹 -->
    <properties>
        <property name="LOG_HOME">./WebAppLogs/logs</property>
    </properties>
    <!--先定义所有的appender-->
    <appenders>
        <!--这个输出控制台的配置-->
        <console name="Console" target="SYSTEM_OUT">
            <!--输出日志的格式-->
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{1.} - %m%n"/>
            <!--<PatternLayout pattern="[%d{HH:mm:ss:SSS}] - (%F:%l) - %m%n"/>-->
            <!--<PatternLayout pattern="[%d{HH:mm:ss:SSS}] (%F:%L) %m%n" />-->
        </console>
        <!-- 这个会打印出所有的info及以下级别的信息，每次大小超过size，则这size大小的日志会自动存入按年份-月份建立的文件夹下面并进行压缩，作为存档-->
        <!-- info日志配置 -->
        <RollingFile name="RollingFileInfo" immediateFlush="true" fileName="${LOG_HOME}/info.log"
                     filePattern="${LOG_HOME}/$${date:yyyy-MM-dd}/info_%d{yyyy-MM-dd-HH}-%i.log.zip">
            <!--控制台只输出level及以上级别的信息（onMatch），其他的直接拒绝（onMismatch）-->
            <ThresholdFilter level="info" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{HH:mm:ss:SSS}] - [%t] [%p] - %logger{36} - %m%n"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true"/>
                <SizeBasedTriggeringPolicy size="10 MB"/>
            </Policies>
            <DefaultRolloverStrategy max="20">
                <Delete basePath="${LOG_HOME}/$${date:yyyy-MM-dd}/" maxDepth="2">
                    <IfFileName glob="info_*.zip"/>
                    <IfLastModified age="168H"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>
    </appenders>
    <!--然后定义logger，只有定义了logger并引入的appender，appender才会生效-->
    <loggers>
        <!--过滤掉spring和mybatis的一些无用的DEBUG信息-->
        <logger name="org.springframework" level="INFO"/>
        <logger name="org.mybatis" level="INFO"/>
        <root level="all">
            <appender-ref ref="Console"/>
            <appender-ref ref="RollingFileInfo"/>
        </root>
    </loggers>
</configuration>
```

## 日志实现-slf4j(LogFactory)

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

## 日志实现Log4j(LogManager)

```java
public class LogUtilManager {
    /**
     * debug级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void debug(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz.getName());
        logger.debug(msg, params);
    }

    /**
     * trace级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void trace(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz.getName());
        logger.trace(msg, params);
    }

    /**
     * info级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void info(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz.getName());
        logger.info(msg, params);
    }

    /**
     * warn级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void warn(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz);
        logger.warn(msg, params);
    }

    /**
     * error级别日志输出
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void error(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz);
        logger.error(msg, params);
    }

    /**
     * fatal日志
     * @param clazz 类
     * @param msg 日志
     * @param params 其他参数
     */
    public static void fatal(Class clazz, String msg, Object... params) {
        Logger logger = LogManager.getLogger(clazz);
        logger.fatal(msg, params);
    }
}
```

## 两种实现的调用

```java
void testLog() {
    LogUtil.info(ConfigApplicationTests.class, "this is logfactory info log");
    LogUtilManager.info(ConfigApplicationTests.class, "this is logmanager info log");
}
```

## 日志输出

![](https://javanorth-1251602255.cos.ap-chengdu.myqcloud.com/img/log.jpg)

## 总结

本文总结了`springboot`集成`log4j2`，当然实际业务情况也有才有logback+slf4j等方式来实现，`log4j2`在各个方面都表现优异。

## 参考文章

* [1].聊一聊log4j2配置文件log4j2.xml.https://www.cnblogs.com/hafiz/p/6170702.html
* [2].Log4j2的日志配置文件，log4j2.xml文件的配置.https://www.cnblogs.com/hyyq/p/7171227.html
* [3].log4j网站.http://logging.apache.org/log4j/2.x/index.html