package com.min.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author zhangjiamin
 * @date 2020/11/23 17:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    //BigDecimal用来对超过16位有效位的数进行精确的运算
    private BigDecimal money;

    private Integer status;     //表示订单状态 0：创建中  1：已完成
}
