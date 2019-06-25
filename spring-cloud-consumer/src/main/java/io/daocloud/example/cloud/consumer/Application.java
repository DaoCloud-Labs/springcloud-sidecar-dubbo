package io.daocloud.example.cloud.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class Application {



    public Application(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }


    private final RestTemplate restTemplate;


    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return restTemplate.getForObject("http://dubbo-consumer-sidecar/hello?name={0}", String.class, name);
    }
}
