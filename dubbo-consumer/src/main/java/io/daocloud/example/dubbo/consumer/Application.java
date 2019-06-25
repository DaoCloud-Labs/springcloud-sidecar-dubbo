package io.daocloud.example.dubbo.consumer;

import io.daocloud.example.dubbo.provider.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ImportResource("classpath:consumer.xml")
@RestController
public class Application {


    @Reference(url = "dubbo://127.0.0.1:20880")
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return helloService.hello(name);
    }


    @RequestMapping("/health.json")
    public Map<String, String> health() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("status", "UP");
        return map;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
