此项目为kafka demo

    该项目只是个demo，相关topic，server，id需要自己去申请，并填写到kafka.properties中
    更好的测试方法：到kafkaconsumerDemo中，将start方法改为public static void main(),这样会方便测试

kafka简介：https://help.aliyun.com/document_detail/68327.html?spm=a2c4g.11186623.6.564.1fa13484CeznCL
官网示例：https://github.com/AliwareMQ/aliware-kafka-demos/tree/master/kafka-java-demo/src/main/java/com/aliyun/openservices/kafka/ons

    生产者消费者消息队列常用有以下几种：
        RabbitMQ(常用)
        Kafka（常用）       
        ActiveMQ
        ZeroMQ

服务获取途径：
    
    1）常用是直接从aliyun去买服务
    2）也可以自己搭建，可参考https://blog.csdn.net/u012689336/article/details/52739042


消费者最重要的方法即：poll(),该方法包括心跳，重平衡，数据拉取，协作等

poll(timeout: 1000):每1000ms拉取一次数据， props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 30);  设置一次拉取30个

