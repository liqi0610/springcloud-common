package cn.v5cn.springboot.redisson.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@RestController
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SendReadMsg sendReadMsg;

    @Autowired
    private RedissonClient redisson;

    @GetMapping("/index")
    public Object index() {
        redisTemplate.boundValueOps("aaa").set("aaa");
        return "dddd";
    }

    @GetMapping("/index2")
    public Object index2() {

        sendReadMsg.sendMsg("dda" + System.currentTimeMillis());
//        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(demo);
//        delayedQueue.offer("hello",10, TimeUnit.SECONDS);
//        String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println("消息发送完成：" + format);
//        delayedQueue.destroy();
        return "aaaa";
    }

    @Async
    public void readMsg() {
        try {
            while (true) {
                String poll = sendReadMsg.readMsg();
                String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                System.out.println("收到消息：" + format);
                System.out.println("ddddddddddddd: " + poll + "\n\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //redisson.shutdown();
        }
    }
}
