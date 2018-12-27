package cn.v5cn.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *     延时队列的创建
 * </p>
 * @author ZYW
 * @version 1.0
 * @date 2018/12/27 11:39
 */
@Configuration
public class DelayRabbitMQConfig {

    public static final String QUEUE_NAME = "delay-queue";

    public static final String EXCHANGE_NAME = "delay-exchange";

    public static final String X_DELAYED_MESSAGE = "x-delayed-message";

    @Bean("delayExchange")
    public CustomExchange delayExchange() {
        /*Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE_NAME, X_DELAYED_MESSAGE, true, false, args);*/
        return (CustomExchange)new ExchangeBuilder(EXCHANGE_NAME,X_DELAYED_MESSAGE).durable(true).withArgument("x-delayed-type","direct").build();
    }

    @Bean("delayQueue")
    public Queue delayQueue() {
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean("delayExchangeQueueBinding")
    public Binding delayExchangeQueueBinding(CustomExchange delayExchange, Queue delayQueue) {
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(QUEUE_NAME).noargs();
    }
}
