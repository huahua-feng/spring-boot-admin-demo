package com.feng.springboot.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RocketMQDemoListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        try {
            for (MessageExt messageExt : msgs) {
                if (messageExt.getReconsumeTimes() > 1){
                    continue;
                }
                String topic = messageExt.getTopic();
                int queueId = messageExt.getQueueId();
                String message = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                System.out.println("the topic: " + topic + "\tqueueId:" + queueId + "\t body：" + message);
            }
        } catch (Exception e) {
            //retry
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
