package cn.v5cn.springboot.redisson.controller;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class IndexController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redisson;

    @GetMapping("/index")
    public Object index() {
        redisTemplate.boundValueOps("aaa").set("aaa");
        return "dddd";
    }

    @GetMapping("/index2")
    public Object index2() {

        RBlockingQueue<String> demo = redisson.getBlockingQueue("demo");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(demo);
        delayedQueue.offer("hello",10, TimeUnit.SECONDS);
        delayedQueue.destroy();
        return "aaaa";
    }

    @Async
    public void readMsg() {
        RBlockingQueue<String> demo = redisson.getBlockingQueue("demo");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(demo);
        try {
            while (true) {
                String poll = demo.take();
                System.out.println("ddddddddddddd: " + poll);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            delayedQueue.destroy();
            redisson.shutdown();
        }
    }
}