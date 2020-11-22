package com.min.soringcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.min.springcloud.entities.CommonResult;

/**
 * @author zhangjiamin
 * @date 2020/11/22 15:54
 *
 * 此类为Sentinel服务降级的fallback方法类
 */


public class Myhandler {

    public static CommonResult handlerException(BlockException e){
        return new CommonResult(404,e.getClass().getCanonicalName()+"\t 服务器不可用！---------handlerException");
    }

    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(404,e.getClass().getCanonicalName()+"\t 服务器不可用！---------handlerException2");
    }
}
