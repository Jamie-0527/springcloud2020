package com.min.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.min.springcloud.alibaba.domain.CommonResult;
import com.min.springcloud.alibaba.domain.Order;
import com.min.springcloud.alibaba.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangjiamin
 * @date 2020/11/23 22:04
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    @SentinelResource(fallback = "fallback")
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功！");
    }
    public CommonResult fallback(Order order){
        return new CommonResult(402,"账户扣除失败！账户ID："+order.getUserId());
    }

    //获取全局唯一ID
    @GetMapping("/order/id")
    public List<Long> getGlobalId(){
//        Long idBySnowFlake = orderService.getIDBySnowFlake();
//        return idBySnowFlake;

        //多线程获取全局ID
        List<Long> list = null;
        //创建10个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i=0;i<40;i++){
            //模拟10个线程分担40个请求
            threadPool.submit(() ->{
                Long id = orderService.getIDBySnowFlake();
                System.out.println(id);
                list.add(id);
            });
        }
        threadPool.shutdown();
        return list;

    }
}
