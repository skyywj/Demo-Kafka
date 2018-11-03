package com.sky.hrpro.kafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sky.hrpro.util.JsonUtils;
import com.sky.hrpro.util.KafkaConfig;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: CarryJey @Date: 2018/9/28 下午4:09
 */

/**
 * kafkaService 消费者demo start()启动kafka消费者进行消费消息
 */
@Service
public class KafkaConsumerService {

    @Value("${kafka.topic.test}")
    private String topic;

    KafkaConsumer<String, String> consumer;

    public void start() {
        if (consumer == null) {
            return;
        } else {
            consumer = new ConsumerConfig().init(topic);
        }

        //循环消费消息
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                //必须在下次poll之前消费完这些数据, 且总耗时不得超过SESSION_TIMEOUT_MS_CONFIG
                //建议开一个单独的线程池来消费消息，然后异步返回结果
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    String value = record.value();
                    solve(value);
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(1000);
                } catch (Throwable ignore) {

                }
                //参考常见报错: https://help.aliyun.com/document_detail/68168.html?spm=a2c4g.11186623.6.567.2OMgCB
                e.printStackTrace();
            }
        }
    }

    public void solve(String msgJson) {
        if (msgJson == null || msgJson.isEmpty()) {
            return;
        }

        //解析键值串为map
        Map<String, String> request = JsonUtils.parseMap(msgJson);
        //分别对不同的消息进行处理
        if (request.get("eventType").equals(EventType.MSG_EVENT_TYPE)) {
            solveTest(request, msgJson);
        }
    }

    //异步处理
    @Async("taskScheduler")
    public void solveTest(Map<String, String> request, String msgJson) {
        if (request != null && request.get("eventType").equals(EventType.MSG_EVENT_TYPE)) {

            System.out.println("do some thing to deal with ***");

            String name = request.get("name");
            String id = request.get("id");
            String age = request.get("age");
            //以map键值的方式取值，然后传到相关方法中去进行处理
        }
    }
}
