package com.min.springcloud.alibaba.controller;

import com.min.springcloud.alibaba.domain.CommonResult;
import com.min.springcloud.alibaba.service.StorageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjiamin
 * @date 2020/11/24 14:25
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId, Integer count){
        storageService.decrease(productId, count);
        return new CommonResult(200,"库存扣减成功！");
    }
}
