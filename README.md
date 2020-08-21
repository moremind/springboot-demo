<p align="center">
    <img src="https://javanorth-1251602255.cos.ap-chengdu.myqcloud.com/img/github/springboot-demo/logo.png"/><br><br>
    <img src="https://img.shields.io/badge/Build--Project-Success-brightgreen">
    <img src="https://img.shields.io/badge/Java--Version-Java8-yellow">
    <img src="https://img.shields.io/badge/Springboot-2.x-brightgreen">
    <img src="https://img.shields.io/badge/MySQL--Server-8.0.15-orange"><br>
    <img src="https://img.shields.io/badge/-druid-orange">
    <img src="https://img.shields.io/badge/-jpa-orange">
    <img src="https://img.shields.io/badge/-elasticsearch-orange">
    <img src="https://img.shields.io/badge/-log4j2-orange">
    <img src="https://img.shields.io/badge/-logback-orange">
    <img src="https://img.shields.io/badge/-mail-orange">
    <img src="https://img.shields.io/badge/-mybatis-orange">
    <img src="https://img.shields.io/badge/-mybatisplus-orange">
    <img src="https://img.shields.io/badge/-netty-orange">
    <img src="https://img.shields.io/badge/-rabbitmq-orange">
    <img src="https://img.shields.io/badge/-redis-orange">
    <img src="https://img.shields.io/badge/-securtity-orange">
    <img src="https://img.shields.io/badge/-shiro-orange">
    <img src="https://img.shields.io/badge/-swagger-orange">
    <img src="https://img.shields.io/badge/-websocket_serverendpoint-orange">
    <img src="https://img.shields.io/badge/-websocket_wshandler-orange">
</p>

## Springboot-Demo Introduction
> 本项目为Springboot整合目前已知的大部分框架实现简单的案例，主要有各类数据源、日志框架、中间件、以及其他样例的整合。
> 该项目致力于为每个单独的项目**提供详细的说明文档以及整合方案(思路)**，并且**保证每个单独的项目能够独立运行。**

### Project Version
**Java Version: Java8**<br>
**Springboot Version: 2.x**<br>
**MySQL Server: 8.0.15**<br>
**Lombok: 0.28-2019.2**

### Application Configuration and Database
1. 所有资源都存放在`src/main/resource`目录下
2. `src/main/resource`下的`*.sql`文件都是数据库导出文件

## Springboot-Demo Example
* **springboot-druid**: springboot整合druid数据源实现数据库监控
* **springboot-elasticsearch** : sprinboot整合elasticsearch
* **springboot-init**: springboot初始化mybatis/logback/分包打包
* **springboot-jpa**: springboot整合jpa实现简单的jpa接口
* **springboot-logging**: 
    * **springboot-log4j2**: springboot整合log4j2
    * **springboot-logback**: springboot整合logback
* **springboot-mail**: springboot整合mail实现不同模式的邮件发送
* **springboot-mybatis**: springboot整合mybatis实现简单的增删改查案例
* **springboot-mybatis-multidatasource**: springboot整合mybatis实现多数据源
* **springboot-mybatisplus**: springboot整合mybatisplus实现简单mybatisplus接口
* **springboot-netty**: springboot整合netty实现http-server与websocket-server
* **springboot-rabbitmq**: springboot整合rabbitmq实现mq的订阅发布、广播模式
* **springboot-redis**: springboot整合redis实现简单的redis接口以及通过redis实现接口幂等性
    * **example_one**: 通过redis-util实现简单的redis接口调用
    * **example_two**: 通过redis-util实现接口幂等性
* **springboot-security**: springboot整合security实现接口授权访问
* **springboot-shiro**: springboot整合shiro实现简单的权限访问
* **springboot-swagger**: springboot整合swagger实现restful接口文档
* **springboot-websocket**: 
    * **serverendpoint**: springboot通过serverendpoint方式设计websocket
    * **wshandler**: springboot通过wshandler方式实现websocket

## Pull Request
欢迎各位gay友一起协同成长~~

## Issue
请转移至issue页面提交您的疑问！[issue](https://github.com/hirCodd/springboot-demo/issues)

## Contact Me
email: hefengen@hotmail.com

## License
[MIT](https://github.com/hirCodd/springboot-demo/blob/master/LICENSE)

## Thanks
**[@hirCodd](https://github.com/hirCodd)**
