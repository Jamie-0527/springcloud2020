package com.min.springcloud.alibaba.service.impl;

import com.min.springcloud.alibaba.dao.AccountDao;
import com.min.springcloud.alibaba.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

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
    public void decrease(Long userId, BigDecimal money) {
        log.info("=====>account-service扣减账户余额begin");
        accountDao.decrease(userId, money);
        log.info("=====>account-service扣减账户余额end");
    }
}
