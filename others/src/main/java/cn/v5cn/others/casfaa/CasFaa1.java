package cn.v5cn.others.casfaa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CAS和FAA的操作系统原语
 * @author ZYW
 * @version 1.0
 * @date 2019-09-17 22:01
 */
public class CasFaa1 {
    public static void main(String[] args) throws InterruptedException {
        //long[] balance = { 0L };
        AtomicLong balance = new AtomicLong(0);
        int count = 10000;
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch cdl = new CountDownLatch(count);
        ExecutorService pool = Executors.newFixedThreadPool(100);
        CasFaa1 casFaa = new CasFaa1();
        for(int i = 0; i < count; i++) {
            pool.execute(() -> {
                // 加锁方式
                //casFaa.transfer(balance,1,lock,cdl);
                // CAS原语操作
                //casFaa.transferCas(balance,1,cdl);
                // FAA原语操作
                casFaa.transferFaa(balance,1,cdl);
            });
        }
        cdl.await();
        pool.shutdown();

        //System.out.println("转账结果：" + balance[0]);
        System.out.println("转账结果：" + balance);
    }

    public void transfer(long[] balance, int amount, ReentrantLock lock, CountDownLatch cdl) {
        try {
            lock.lock();
            balance[0] = balance[0] + amount;
        } finally {
            cdl.countDown();
            lock.unlock();
        }
    }

    //CAS
    public void transferCas(AtomicLong balance, int amount, CountDownLatch cdl) {
        //balance.getAndAdd(amount);
        for(;;) {
            long oldValue = balance.get();
            long newValue = oldValue + amount;
            if (balance.compareAndSet(oldValue, newValue)) {
                cdl.countDown();
                break;
            }
        }
    }
    //FAA
    public void transferFaa(AtomicLong balance, int amount, CountDownLatch cdl) {
        balance.addAndGet(amount);
        cdl.countDown();
    }
}
