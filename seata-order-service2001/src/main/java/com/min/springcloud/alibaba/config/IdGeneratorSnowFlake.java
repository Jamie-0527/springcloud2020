package com.min.springcloud.alibaba.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhangjiamin
 * @date 2020/11/25 12:50
 *
 * 此类为HuTool工具雪花算法的引用配置类
 */

@Slf4j
@Component
public class IdGeneratorSnowFlake {

    private long workerId = 0;              //取值0-31
    private long datacenterId = 0;          //取值0-31

    //传入机器Id
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    //初始化，获取当前机器Ipv4的机器码
    @PostConstruct      //次注解在引入依赖后运行，完成初始化操作
    public void init(){
        try{
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId：{}"+workerId);
        }catch (Exception e){
            e.printStackTrace();
            log.warn("获取当前机器的workerId失败！"+ e);
            workerId = NetUtil.getLocalhost().hashCode();
        }
    }

    public synchronized long sonwflakeId(){
        return snowflake.nextId();
    }

    //重构，手动传入参数
    public synchronized long sonwflakeId(long workerId, long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

}
