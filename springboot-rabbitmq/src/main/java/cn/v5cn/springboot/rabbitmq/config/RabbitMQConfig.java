package cn.v5cn.springboot.rabbitmq.config;

import cn.v5cn.springboot.rabbitmq.util.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZYW
 * @version 1.0
 * @date 2018/12/26 17:26
 */
@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 声明一个车辆驶入队列 支持持久化.并绑定
     *
     * @return the queue
     */
    @Bean("driveInQueue")
    public Queue entryQueue() {
        Exchange exchange = ExchangeBuilder.fanoutExchange(Constants.VEHICLE_DRIVE_IN_EXCHANGE).durable(true).build();
        Queue queue = QueueBuilder.durable(Constants.VEHICLE_DRIVE_IN_QUEUE + "num01").build();
        BindingBuilder.bind(queue).to(exchange);

        return queue;
    }



}
