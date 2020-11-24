package com.min.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjiamin
 * @date 2020/11/20 12:57
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "==========testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "==========testB";
    }

    @GetMapping("/testD")
    public String testD(){
       /* try{
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        int a=10/0;
        log.info("==========testD");
        return "==========testD";
    }

}
