package com.min.springcloud.alibaba.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangjiamin
 * @date 2020/11/24 14:17
 */
public interface StorageService {

    //扣减库存
    void decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
