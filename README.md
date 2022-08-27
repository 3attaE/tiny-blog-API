# tiny-blog-API
A tiny blog backend server demo

## API 文档

[文档链接](https://github.com/3attaE/tiny-blog-API/blob/main/API.md)

## Quick Start

### 本地部署

测试登录用户名 `test` 密码 `test`

#### step 1

准备数据库

导入 `DDL.sql` 到 MySQL

#### step 2

复制 `/src/main/resources/application.properties.demo` 为 `application.properties`

#### step 3

补充 MySQL 和 Redis 链接信息

### step 4

在项目主文件夹下 `mvn package`

### step 5

`cd target && java -jar tiny-blog-api-0.0.1-SNAPSHOT.jar`


## 系统设计

### 技术选型

- SpringBoot 2.7.3
- MySQL 5.7.36
- Redis 7.0.4

### 登录模块

采用 JSON web Token 鉴权。

已标注的 HTTP 请求携带 Authorization Token 可通过权限验证。

### 缓存方案

缓存点赞数，当数值更新时，删除对应 Key。
访问 Key 若为 null，从 MySQL 记录中同步最新点赞数。

### 全文索引

利用 MySQL 5.7.6 版本之后的全文索引功能

## 实现如下功能

- [x] 登录/退出
- [x] 发布/修改文章
- [x] 文章列表
- [x] 文章评论
- [x] 文章点赞
- [x] 全文搜索


