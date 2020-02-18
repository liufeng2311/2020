package com.beiming.thread.threadpoolexecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * newCachedThreadPool线程池示例
 * 核心线程数为0,最大线程数为Integer.MAX_VALUE,存活时间为60秒
 * 队列SynchronousQueue,当有线程来取任务时,才会插入任务
 *
 */

@Slf4j
public class CachedThreadPoolDemo1 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 50; i++){
            //该行代码的作用只创建一个线程去执行,没有该行代码将会创建50个线程,因为SynchronousQueue队列必须由线程去消费才存入
            TimeUnit.SECONDS.sleep(1);
            int j = i;
            executor.execute(() -> {
                log.info("{}处理了任务{}", Thread.currentThread().getName(), j);
            });
        }
    }
}
