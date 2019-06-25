package io.daocloud.example.dubbo.provider;

public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "hello " + name;
    }
}
