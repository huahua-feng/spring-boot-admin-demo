package com.feng.springboot.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "org.rocketmq")
@Data
public class RocketMQProperties {

    private String nameSrvs;
    private String producerGroup;
    private String consumerGroup;
}
