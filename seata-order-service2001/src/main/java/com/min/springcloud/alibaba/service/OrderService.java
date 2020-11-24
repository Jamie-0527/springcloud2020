package com.min.springcloud.alibaba.service;

import com.min.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @author zhangjiamin
 * @date 2020/11/23 21:13
 */
public interface OrderService {

    //新建订单
    void create(Order order);

    //修改订单状态 0改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);

}
