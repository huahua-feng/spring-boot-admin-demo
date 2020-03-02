package com.feng.springboot.rocketmq.config;

import com.feng.springboot.rocketmq.consumer.RocketMQDemoListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(RocketMQProperties.class)
@Configuration
public class RocketMQInitConfig {

    @Autowired
    private RocketMQProperties rocketMQProperties;

    @Bean
    public MQProducer getMQProducer() throws MQClientException {
        DefaultMQProducer mqProducer = new DefaultMQProducer();
        mqProducer.setNamesrvAddr(rocketMQProperties.getNameSrvs());
        mqProducer.setProducerGroup(rocketMQProperties.getProducerGroup());
        mqProducer.setSendMsgTimeout(1000);
        mqProducer.setVipChannelEnabled(false);
        //set other properties
        //............

        mqProducer.start();

        return mqProducer;
    }

    @Autowired
    private RocketMQDemoListener rocketMQDemoListener;

    @Bean
    public MQConsumer getMQConsumer() throws MQClientException {
        //instance
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        //group
        consumer.setConsumerGroup(rocketMQProperties.getConsumerGroup());
        //setNamesrvAddr，cluster with ; spit
        consumer.setNamesrvAddr(rocketMQProperties.getNameSrvs());

        //consumer offset
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        //subscribe, the subExpression is tags in message send
        //subscribe topic store in map
        consumer.subscribe("demoTopic", "tags-1");
        //can subscribe more
        //consumer.subscribe("demoTopic2", "*");
        //or use setSubscription, method is deprecated
        //consumer.setSubscription();

        //batch consumer max message limit
        consumer.setConsumeMessageBatchMaxSize(1000);
        //min thread
        consumer.setConsumeThreadMin(10);
        consumer.registerMessageListener(rocketMQDemoListener);


        consumer.start();

        return consumer;
    }
}
