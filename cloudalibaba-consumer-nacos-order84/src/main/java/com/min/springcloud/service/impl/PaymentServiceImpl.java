package com.min.springcloud.service.impl;

import com.min.springcloud.entities.CommonResult;
import com.min.springcloud.entities.Payment;
import com.min.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @author zhangjiamin
 * @date 2020/11/22 21:39
 */
@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(404,"服务降级--------PaymentServiceImpl",new Payment(id,"errorSerial"));
    }
}
