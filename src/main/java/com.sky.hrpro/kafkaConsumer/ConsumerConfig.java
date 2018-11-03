package com.sky.hrpro.kafkaConsumer;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author yanwenjie
 * @since 2018/11/3
 */
@Service
public class ConsumerConfig {
    @Value("${kafka.bootstrap.servers}")
    private String kafkaServer;

    @Value("${kafka.consumer.test}")
    private String consumerId;

    public KafkaConsumer<String,String> init(String topic){
        Properties props = new Properties();
        // 设置接入点，请通过控制台获取对应 Topic 的接入点
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        // 接入协议
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "PLAINTEXT");
        // 两次 poll 之间的最大允许间隔
        // 请不要改得太大，服务器会掐掉空闲连接，不要超过 30000
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 25000);
        // 每次 poll 的最大数量
        // 注意该值不要改得太大，如果 poll 太多数据，而不能在下次 poll 之前消费完，则会触发一次负载均衡，产生卡顿
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);
        // 消息的反序列化方式
        props.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(
                org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // 当前消费实例所属的消费组，请在控制台申请之后填写
        // 属于同一个组的消费实例，会负载消费消息
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, consumerId);
        // 构造消息对象，也即生成一个消费实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        // 设置消费组订阅的 Topic，可以订阅多个
        // 如果 GROUP_ID_CONFIG 是一样，则订阅的 Topic 也建议设置成一样
        List<String> subscribedTopics = new ArrayList<String>();
        // 如果需要订阅多个 Topic，则在这里 add 进去即可
        // 每个 Topic 需要先在控制台进行创建
        subscribedTopics.add(topic);
        consumer.subscribe(subscribedTopics);

        return consumer;
    }
}
