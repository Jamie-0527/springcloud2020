package com.min.springcloud.service;

import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    //模拟正常响应
    public String paymentInfo_OK(Integer id);

    //模拟异常出错
    public String paymentTimeOut(Integer id);

    //服务熔断
    public String paymentCircuitBreaker(Integer id);
}
