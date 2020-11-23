package com.min.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangjiamin
 * @date 2020/11/22 16:47
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerOrderMain84 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain84.class,args);
    }
}
