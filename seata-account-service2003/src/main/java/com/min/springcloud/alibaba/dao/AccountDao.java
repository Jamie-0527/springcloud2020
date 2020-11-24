package com.min.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author zhangjiamin
 * @date 2020/11/24 15:00
 */
@Mapper
public interface AccountDao {

    //扣减账户余额
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
