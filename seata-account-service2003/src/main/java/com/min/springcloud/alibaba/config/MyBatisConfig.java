package com.min.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangjiamin
 * @date 2020/11/23 22:08
 */
@Configuration
@MapperScan({"com.min.springcloud.alibaba.dao"})
public class MyBatisConfig {
}
