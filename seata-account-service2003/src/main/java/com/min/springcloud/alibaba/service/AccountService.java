package com.min.springcloud.alibaba.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @author zhangjiamin
 * @date 2020/11/24 15:13
 */
public interface AccountService {

    //扣减账户余额
    void decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
