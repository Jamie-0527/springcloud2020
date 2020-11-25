package com.min.springcloud.alibaba.service.impl;

import com.min.springcloud.alibaba.config.IdGeneratorSnowFlake;
import com.min.springcloud.alibaba.dao.OrderDao;
import com.min.springcloud.alibaba.domain.Order;
import com.min.springcloud.alibaba.service.AccountService;
import com.min.springcloud.alibaba.service.OrderService;
import com.min.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangjiamin
 * @date 2020/11/23 21:32
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;
    //注入工具类，获取全局唯一ID
    @Autowired
    private IdGeneratorSnowFlake idGeneratorSnowFlake;

    @Override
    public void create(Order order) {

        //1、创建订单
        log.info("=====>订单微服务开始创建订单");
        orderDao.create(order);
        //2、更新Count
        log.info("=====>订单微服务开始更新库存数量，扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----------------------------------扣减end");
        //3、更新余额
        log.info("=====>订单微服务更新账户余额，扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----------------------------------扣减end");
        //4、修改订单状态
        log.info("=====>订单微服务修改订单状态begin");
        orderDao.update(order.getUserId(),1);
        log.info("=====>订单微服务修改订单状态end");
    }

    @Override
    public void update(Long userId, Integer status) {

    }

    @Override
    public Long getIDBySnowFlake() {
        long sonwflakeId = idGeneratorSnowFlake.sonwflakeId();
        return sonwflakeId;
    }
}
