package com.min.springcloud.service;

import com.min.springcloud.entities.CommonResult;
import com.min.springcloud.entities.Payment;
import com.min.springcloud.service.impl.PaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhangjiamin
 * @date 2020/11/22 21:35
 */
@FeignClient(value = "nacos-payment-provider",fallback = PaymentServiceImpl.class)
public interface PaymentService {

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);
}
