package com.beiming.thread.highconcurrencycount;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * 模拟50个线程,每个线程进行100万次递增
 * synchronized 关键字实现
 */


@Slf4j
public class HighConcurrencyCountDemo4 {

    static LongAccumulator count = new LongAccumulator((x,y) -> x+y, 0);

    //每个线程模拟100万次递增
    static void countAdd(CountDownLatch count1) {
        ExecutorService executor = Executors.newFixedThreadPool(9);
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try {
                    Instant start = Instant.now();
                    for (int j = 0; j < 10000000; j++) {
                        count.accumulate(1);
                    }
                    Instant end = Instant.now();
                    log.info("{}模拟耗时{},开始时间{},结束时间{}", Thread.currentThread().getName(), Duration.between(start, end).getSeconds(), start, end);
                }finally {
                    count1.countDown();
                }
            });
        }
        executor.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        count.reset();
        Instant start = Instant.now();
        CountDownLatch count1 = new CountDownLatch(50);
        countAdd(count1);
        count1.await();
        Instant end = Instant.now();
        TimeUnit.SECONDS.sleep(13);
        log.info("本次模拟耗时{}", Duration.between(start, end).getSeconds());
        log.info("{}", count);
    }
}
