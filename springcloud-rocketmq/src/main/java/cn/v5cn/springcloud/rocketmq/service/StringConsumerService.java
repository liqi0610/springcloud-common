package cn.v5cn.springcloud.rocketmq.service;

import cn.v5cn.springcloud.rocketmq.util.Constants;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.starter.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = Constants.SPRING_TOPIC,consumerGroup = Constants.SPRING_TOPIC_CONSUMER)
public class StringConsumerService implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("接收到消息时间1：" + System.currentTimeMillis());
        System.out.println(s);
    }
}
