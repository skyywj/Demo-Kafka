package com.sky.hrpro.service;

import com.sky.hrpro.util.LoggerUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;

/**
 * @Author: YanWenjie
 * @Date: 2018/9/28 下午4:56
 */
public class TaskRunner implements ApplicationRunner,Ordered {

    KafkaConsumerDemo kafkaConsumerDemo;

    //设置顺序
    @Override
    public int getOrder() {
        return 2;
    }

    /**
     *通过run方法启动服务调用kafkaConsumerDemo.start();
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LoggerUtils.info("task runner for kafaka demo test");
        kafkaConsumerDemo.start();
    }
}
