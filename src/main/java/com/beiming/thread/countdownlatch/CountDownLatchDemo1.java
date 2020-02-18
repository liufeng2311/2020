package com.beiming.thread.countdownlatch;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch被称之为闭锁,它可以使一个或一批线程在闭锁上等待，等到其他线程执行完相应操作后，闭锁打开，这些等待的线程才可以继续执行。
 * 闭锁在内部维护了一个倒计数器。通过该计数器的值来决定闭锁的状态，从而决定是否允许等待的线程继续执行
 * 使用方法：
 *      1.CountDownLatch的构造方法指定需要监控线程的数量
 *      2.CountDownLatch作为参数传入线程,执行完逻辑后在finally代码块中执行countDown()方法
 *      3.CountDownLatch执行await()方法,等待线程执行完,我们也可以指定等待时间,若超过时间直接方法
 */

@Slf4j
public class CountDownLatchDemo1 extends Thread{

    int sleepSeconds;

    CountDownLatch countDownLatch;

    public CountDownLatchDemo1(String name, int sleepSeconds, CountDownLatch countDownLatch) {
        super(name);
        this.sleepSeconds = sleepSeconds;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        Instant start = Instant.now();
        CountDownLatchDemo1 t1 = new CountDownLatchDemo1("t1", 3, countDownLatch);
        CountDownLatchDemo1 t2 = new CountDownLatchDemo1("t2", 5, countDownLatch);
        t1.start();
        t2.start();
        countDownLatch.await(0,TimeUnit.SECONDS);
        Instant end = Instant.now();
        log.info("此次处理用时为{}秒", Duration.between(start,end).getSeconds());
    }
}
