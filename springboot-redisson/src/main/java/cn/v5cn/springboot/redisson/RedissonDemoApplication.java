package cn.v5cn.springboot.redisson;

import cn.v5cn.springboot.redisson.controller.IndexController;
import cn.v5cn.springboot.redisson.controller.SendReadMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class RedissonDemoApplication {

    @Autowired
    private SendReadMsg sendReadMsg;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RedissonDemoApplication.class, args);
        IndexController bean = applicationContext.getBean(IndexController.class);
        //bean.readMsg();
    }


    @PostConstruct
    private void msgRead() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {
                try {
                    String msg = sendReadMsg.readMsg();
                    String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    System.out.println("收到消息：" + format + " MSG: " + msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
