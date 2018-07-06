##哪都通跑腿系统

哪都通跑腿项目基于Spring Boot，以Spring Boot 为起点，使用Spring MVC, Spring Data JPA, Spring AOP等框架。

前端页面基于 Bootstrap 4

##技术栈(技术选型)：
###后端:

核心框架 ：Spring Boot 1.5.10.RELEASE

视图框架：Spring MVC

持久层框架：Spring Data JPA

数据库连接池：Alibaba Druid 1.0.20

日志管理：LogBack

###前端:

主要使用的技术：

基于Bootstrap深度定制前端框架：Bootstrap4

JavaScript: jQuery

##打包部署
切换的项目父目录runner下，执行mvn clean package -Dmaven.test.skip=true, 然后去runner-admin、runner-web target目录查看打包结果

