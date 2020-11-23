package com.min.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.min.springcloud.entities.CommonResult;
import com.min.springcloud.entities.Payment;
import com.min.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhangjiamin
 * @date 2020/11/22 17:00
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//没有任何处理响应
    //@SentinelResource(value = "fallback", fallback = "handleFallback")//负责java代码业务处理的fallback
    //@SentinelResource(value = "fallback", blockHandler = "blockHandle")//负责处理前台Sentinel定义的限流违规
    //倘若fallback和blockHandler都配置的话，触犯了限流规则还是会被进入blockHandler处理，不论是否异常
    @SentinelResource(value = "fallback",
            blockHandler = "blockHandle",
            fallback = "handleFallback",
            //忽略某种异常
            exceptionsToIgnore = IllegalArgumentException.class)
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){

        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException非法参数异常！");
        }else if(result.getData() == null){
            throw new NullPointerException("没有对应的记录！空指针异常-----NullPointerException");
        }
        return result;
    }
    public CommonResult handleFallback(@PathVariable("id") Long id,Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444,"兜底异常handlerFallback ,exception内容\t"+e.getMessage(),payment);
    }
    public CommonResult blockHandle(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445,"此处被Sentinel限流了 ,exception内容\t"+blockException.getMessage(),payment);
    }

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
