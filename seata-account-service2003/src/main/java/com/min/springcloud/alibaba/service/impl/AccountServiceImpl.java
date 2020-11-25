package com.min.springcloud.alibaba.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.min.springcloud.alibaba.dao.AccountDao;
import com.min.springcloud.alibaba.service.AccountService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangjiamin
 * @date 2020/11/24 15:16
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void decrease(Long userId, BigDecimal money) {
        log.info("=====>account-service扣减账户余额begin");
        //模拟超时异常
        try{
            TimeUnit.SECONDS.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        log.info("=====>account-service扣减账户余额end");
    }
}
