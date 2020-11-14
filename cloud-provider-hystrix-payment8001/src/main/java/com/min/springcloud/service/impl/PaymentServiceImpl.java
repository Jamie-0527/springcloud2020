package com.min.springcloud.service.impl;

import com.min.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
