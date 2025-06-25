# miniBOM 后端开发指南

## 环境要求与配置

### 基础环境
- ✅ **Java 版本**：JDK 8
- 🚀 **Spring Boot 版本**：2.7.12
- ✅ **项目状态**：已通过基础功能验证，可成功启动

## 关键配置步骤

### SDK 依赖配置
在运行项目前，**必须**修改 `pom.xml` 文件中的 SDK 依赖路径
### Redis服务启用
在运行项目前，需安装并启用redis服务
```yaml
redis-server.exe
redis-cli.exe
```



### 模块说明
- **User 模块运行模式**：通过调用 SDK API 实现核心功能
- 由于开启了拦截器，除了登录和注册外的其他请求都需要在请求header中携带名为Authorization的token值，使用下面的以提供账号来调用登录接口以从相应中获取token信息
- ### 目前已提供测试账号：
- (1)
- username：user001
- password：abc123@@
-
- (2)
- username：user002
- password：abc123@@



下面是登录接口说明——

## POST 用户登录接口说明

POST /localhost:8080/miniBOM/user/login

用于登录，用户登录成功后，会下发jwt令牌，看是否需要再在后续的对系统的每次请求中，浏览器在请求头header携带token，名称为Authorization，值为下发的token
若检测到用户未登录，http响应401

> Body 请求参数

```yaml
username: "user001"
password: "abc123@"

```

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|body|body|object| 否 |none|
|» username|body|string| 是 |6-32位，数字或字母|
|» password|body|string| 是 |8-32位，数字字母特殊符号|

> 返回示例

```json
{
  "code": 0,
  "result": "SUCCESS",
  "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOjc3ODM3MzIwOTI3MDU0MjMzNiwidXNlcm5hbWUiOiJ1c2VyMDAxIn0sImV4cCI6MTc1MDY0NjE4NX0.pFcnKNwEQkmPZbJcaV_Y-Tuavv64hoYCQvewvaabPYE",
  "errors": null
}
```

