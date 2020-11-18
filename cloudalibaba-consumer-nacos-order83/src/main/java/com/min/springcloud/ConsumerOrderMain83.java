package com.min.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangjiamin
 * @date 2020/11/18 21:49
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain83.class,args);
    }
}
