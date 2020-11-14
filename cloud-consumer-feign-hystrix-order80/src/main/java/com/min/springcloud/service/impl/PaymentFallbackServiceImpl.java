package com.min.springcloud.service.impl;

import com.min.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * 此类为服务降级保障方法类
 */

@Component
public class PaymentFallbackServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务器异常------paymentInfo_OK";
    }

    @Override
    public String paymentTimeOut(Integer id) {
        return "服务器异常------paymentTimeOut";
    }
}
