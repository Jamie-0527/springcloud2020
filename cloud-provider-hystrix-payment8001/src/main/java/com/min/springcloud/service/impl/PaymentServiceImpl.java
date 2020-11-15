package com.min.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.min.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    //模拟正常响应
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池： "+ Thread.currentThread().getName()+"  paymentInfo_OK,id="+id;
    }

    //模拟异常出错
    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentTimeOut(Integer id) {
        //暂停几秒模拟超时异常
        int timeOut=2;
        try{
            TimeUnit.SECONDS.sleep(timeOut);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池： "+ Thread.currentThread().getName()+"  paymentTimeOut,id="+id+"  耗时（秒）"+timeOut;
    }

    public String paymentTimeOutHandler(Integer id) {
        return "服务器繁忙，请稍后再试！";
    }

    //服务熔断

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler",commandProperties = {
            //开启断路器，默认也是开启的，详细请查看HystrixCommandProperties.java
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),
            //设定请求次数，默认20次
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            //设置窗口休眠时间10秒，默认5秒
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            //设置断路器的百分比，失败率达到这个数值就跳闸，默认50
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){

        if (id<0){
            throw new RuntimeException("*******id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();      //等同于Thread.currentThread().getName()
        return Thread.currentThread().getName()+"调用成功！流水号为"+serialNumber;
    }
    public String paymentCircuitBreakerHandler(@PathVariable("id") Integer id){
        return "ID不能为负数，请稍后再试-------ID:"+id;
    }
}
