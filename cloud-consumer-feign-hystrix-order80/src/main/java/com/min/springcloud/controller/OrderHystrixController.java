package com.min.springcloud.controller;

import com.min.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//配置服务降级全局注解
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    //模拟正常
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("***********result:"+result);
        return result;
    }
    //模拟超时
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    //一对一的服务降级处理
//    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String paymentTimeOut(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentTimeOut(id);
        log.info("***********result:"+result);
        return result;
    }

    public String paymentTimeOutHandler(@PathVariable("id") Integer id){
        return "我是80端口的消费者，服务器繁忙，请10秒后重试！";
    }

    //全局服务降级处理方法
    public String payment_Global_FallbackMethod(){
        return "全局处理*******服务器繁忙，请10秒后重试！";
    }
}
