# Dubbo Sidecar Spring Cloud

一个如何将 Dubbo 采用 Sidecar的方式接入  Spring Cloud组件生态

# How-To

- 逻辑结构
![逻辑结构](https://i.loli.net/2019/07/05/5d1eabf187b5a29504.png)

- 流程图
![流程图](https://i.loli.net/2019/07/05/5d1eac2797a1511832.png)


## 对Dubbo应用改造

- 提供Health检查接口，如果已经有了，则忽略。配置在sidecar项目application.yml中。

Health检查接口返回值参考：
```java
    @RequestMapping("/health.json")
    public Map<String, String> health() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("status", "UP");
        return map;
    }
```

```yml
sidecar:
  port: 8081 # dubbo 应用对外服务端口
  health-uri: http://localhost:8081/health.json # dubbo 应用健康检查接口
```

# SideCar配置

- Eureka Server地址

```yml
spring:
  application:
    name: dubbo-consumer-sidecar
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
# 打包

```bash
# 获取源码
git clone https://github.com/DaoCloud-Labs/springcloud-sidecar-dubbo.git
# 进入源码根目录
cd springcloud-sidecar-dubbo
# 编译项目
mvn clean package -DskipTests
# 打包Sidecar项目为Docker镜像
cd sidecar && mvn dockerfile:build
```

# 容器运行

```bash
docker run -e APP_NAME=my-dubbo-app \
-e EUREKA_URL=http://localhost:8761/eureka \
-e ZUUL_URL=http://localhost:8081
daocloud.io/ms_platform/dubbo-sidecar:1.0.0
```
