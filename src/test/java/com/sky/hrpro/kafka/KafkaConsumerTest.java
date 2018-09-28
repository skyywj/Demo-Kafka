package com.sky.hrpro.kafka;

import com.sky.hrpro.kafkaService.KafkaConsumerDemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: CarryJey
 * @Date: 2018/9/28 19:50:29
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class KafkaConsumerTest {

    @Autowired
    private KafkaConsumerDemoService kafkaConsumerDemoService;

    @Test
    public void testFriendAddEvent() {
        String json = "{\"data\":[{\"position\":\"student\",\"username\":\"userZwZ\"}],\"errorCode\":\"0\",\"errorMsg\":\"调用接口成功\"}\n";
        kafkaConsumerDemoService.solve(json);
    }


}
