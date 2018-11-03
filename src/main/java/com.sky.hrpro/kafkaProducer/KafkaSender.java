package com.sky.hrpro.kafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author CarryJey
 * @since 2018/10/31
 * desc: kafka发送类，sevice将待发送的对象转成json字符串直接调用send方法发送给kafka即可
 * entity转json字符串方法：JsonUtils.toJsonString(entity)
 */
@Service
public class KafkaSender {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("$kafka.topic.test")
    private String topic;

    @Value("$kafka.consumer.test")
    private String consumer;

    public void send(String message) {
        LOG.debug("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
    }
}
