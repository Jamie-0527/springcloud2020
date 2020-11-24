package com.min.springcloud.alibaba.service.impl;

import com.min.springcloud.alibaba.dao.StorageDao;
import com.min.springcloud.alibaba.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangjiamin
 * @date 2020/11/24 14:19
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        log.info("=====>storage-service扣减库存begin");
        storageDao.decrease(productId, count);
        log.info("=====>storage-service扣减库存end");
    }
}
