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
