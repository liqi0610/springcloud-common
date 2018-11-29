package cn.v5cn.springcloud.rocketmq;

import org.apache.rocketmq.client.producer.SendResult;
import org.rocketmq.starter.core.producer.RocketMQProducerTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringcloudRocketmqApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudRocketmqApplication.class, args);
    }

    @Resource
    private RocketMQProducerTemplate rocketMQProducerTemplate;

    @Override
    public void run(String... args) throws Exception {

       /* // 以同步的方式发送字符串消息给指定的topic
        SendResult sendResult = rocketMQProducerTemplate.send("test", "Hello, World!");
        // 打印发送结果信息
        System.out.printf("string-topic syncSend1 sendResult=%s %n", sendResult);*/
    }
}
