package com.min.springcloud.alibaba.controller;

import com.min.springcloud.alibaba.domain.CommonResult;
import com.min.springcloud.alibaba.domain.Order;
import com.min.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangjiamin
 * @date 2020/11/23 22:04
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");
    }
}
