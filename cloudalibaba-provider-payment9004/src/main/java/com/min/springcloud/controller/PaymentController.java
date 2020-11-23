package com.min.springcloud.controller;

import com.min.springcloud.entities.CommonResult;
import com.min.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author zhangjiamin
 * @date 2020/11/22 16:40
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    //模拟Dao层访问
    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1l, new Payment(1l, UUID.randomUUID().toString()));
        hashMap.put(2l, new Payment(2l,UUID.randomUUID().toString()));
        hashMap.put(3l, new Payment(3l,UUID.randomUUID().toString()));
    }

    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from server port:"+serverPort,payment);
        return result;
    }
}