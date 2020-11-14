package com.min.springcloud.service;

public interface PaymentService {

    //模拟正常响应
    public String paymentInfo_OK(Integer id);

    //模拟异常出错
    public String paymentTimeOut(Integer id);
}
