package com.beiming.thread.countdownlatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 编写一个并发工具类
 */

@Slf4j
public class CountDownLatchDemo2 {

    public static final int POOL_SIZE;

    static {
        POOL_SIZE = Runtime.getRuntime().availableProcessors();
    }

    public static <T> void dispose(int poolSize, List<T> list, Consumer<T> consumer) throws InterruptedException {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        if (POOL_SIZE > 0) {
            poolSize = Math.min(POOL_SIZE, list.size());
            ExecutorService execute = null;
            try {
                execute = Executors.newFixedThreadPool(poolSize);
                CountDownLatch countDownLatch = new CountDownLatch(list.size());
                for (T item : list) {
                    execute.execute(() -> {
                        try {
                            consumer.accept(item);
                        } finally {
                            countDownLatch.countDown();
                        }
                    });
                }
                countDownLatch.await();
            } finally {
                if (execute != null) {
                    execute.shutdown();
                }
            }
        } else {
            for (T item : list) {
                consumer.accept(item);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> collect = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());
        Instant start = Instant.now();
        CountDownLatchDemo2.dispose(POOL_SIZE,collect, item -> {
            try {
                TimeUnit.SECONDS.sleep(item);
            }catch (InterruptedException e){

            }
        });
        Instant end = Instant.now();
        log.info("花费时间{}秒", Duration.between(start,end).getSeconds());
    }
}
