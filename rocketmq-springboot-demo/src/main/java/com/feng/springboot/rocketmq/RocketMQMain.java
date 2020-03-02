package com.feng.springboot.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class RocketMQMain {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQMain.class, args);
    }

    @Autowired
    private MQProducer producer;

    @RequestMapping(value = "messages", method = RequestMethod.GET)
    public Map<String, String> sendMsg() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, UnsupportedEncodingException {
        Map<String, String> map = new HashMap<>(2);

        // send msg
        int num = 20;
        for (int i = 0; i < num; i++) {
            //message，topic,tags is a mark in consumer,keys is a mark for message query
            Message message = new Message("demoTopic","tags-1", "instanceKeys", ("I`m a " + i + " rocket mq msg!").getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message, 1000);
            System.out.println("send result is\t" + result);
        }

        map.put("isSend", "OK");
        return map;
    }
}
