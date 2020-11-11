package com.min.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced       //使用@LoadBalanced让RestTemplate开启负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
