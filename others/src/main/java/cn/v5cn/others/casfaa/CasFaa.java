package cn.v5cn.others.casfaa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CAS和FAA的操作系统原语
 * @author ZYW
 * @version 1.0
 * @date 2019-09-17 22:01
 */
public class CasFaa {
    public static void main(String[] args) throws InterruptedException {
        long[] balance = {0L};
        int count = 10000;
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch cdl = new CountDownLatch(count);
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        CasFaa casFaa = new CasFaa();
        for(int i = 0; i < count; i++) {
            pool.submit(() -> {
                casFaa.transfer(balance,1,lock,cdl);
            });
        }
        cdl.await();

        System.out.println("转账结果：" + balance[0]);
    }

    public void transfer(long[] balance, int amount, ReentrantLock lock, CountDownLatch cdl) {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getId());
            balance[0] = balance[0] + amount;
        } finally {
            cdl.countDown();
            lock.unlock();
        }
    }
}
