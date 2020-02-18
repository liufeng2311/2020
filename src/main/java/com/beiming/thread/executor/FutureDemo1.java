package com.beiming.thread.executor;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.*;

/**
 * 异步获取线程结果
 */

@Slf4j
public class FutureDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<?> submit = executor.submit(() -> {
            log.info("开始时间{}", Instant.now().toEpochMilli());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("结束时间{}", Instant.now().toEpochMilli());
            return "hello";
        });
        log.info("submit.get()= {}",submit.get());
        executor.shutdown();
    }
}
