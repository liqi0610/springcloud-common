package cn.v5cn.springboot.redisson;

import cn.v5cn.springboot.redisson.controller.IndexController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableAsync
public class RedissonDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RedissonDemoApplication.class, args);
        IndexController bean = applicationContext.getBean(IndexController.class);
        bean.readMsg();
    }


    @PreDestroy
    private void dd() {
        System.out.println("===========================");
        //redissonClient.shutdown();
    }
}
