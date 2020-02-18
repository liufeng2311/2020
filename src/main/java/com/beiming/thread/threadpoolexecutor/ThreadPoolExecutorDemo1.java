package com.beiming.thread.threadpoolexecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的简单使用
 * corePoolSize核心线程数
 * maximumPoolSize最大线程数
 * keepAliveTime存活时间
 * unit存活时间单位
 * workQueue工作队列
 * threadFactory创建线程工作工厂
 * handler拒绝策略
 */

@Slf4j
public class ThreadPoolExecutorDemo1 {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
            5,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10; i++){
            int finalI = i;
            executor.execute(() ->{
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}处理任务{}处理完毕",Thread.currentThread().getName(),finalI);
            });
        }
        executor.shutdown();
        log.info("{}",executor.getActiveCount());
        TimeUnit.SECONDS.sleep(40);
        log.info("{}",executor.getActiveCount());
    }
}
