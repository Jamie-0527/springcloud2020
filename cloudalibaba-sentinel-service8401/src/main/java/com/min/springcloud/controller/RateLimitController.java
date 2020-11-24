package com.min.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.min.springcloud.handler.Myhandler;
import com.min.springcloud.entities.CommonResult;
import com.min.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjiamin
 * @date 2020/11/22 15:32
 */

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource(){
        return  new CommonResult(200,"资源名称限流测试通过！", new Payment(2020l,"serial123456"));
    }
    public CommonResult handleException(BlockException e){
        return new CommonResult(404,e.getClass().getCanonicalName()+"\t 服务器不可用！");
    }

    @GetMapping("/rateLimit/handler01")
    @SentinelResource(value = "handler01",
            blockHandlerClass = Myhandler.class,
            blockHandler = "handlerException")
    public CommonResult handler01(){
        return  new CommonResult(200,"资源名称限流测试通过！", new Payment(2020l,"serial6666666666"));
    }
}
