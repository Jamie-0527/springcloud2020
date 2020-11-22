package com.min.soringcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjiamin
 * @date 2020/11/20 12:57
 */
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "==========testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "==========testB";
    }
}
