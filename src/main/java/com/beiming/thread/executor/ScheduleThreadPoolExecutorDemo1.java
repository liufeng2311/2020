package com.beiming.thread.executor;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.concurrent.*;

/**
 * 实现了 {@link java.util.concurrent.ScheduledExecutorService} 和 {@link java.util.concurrent.ThreadPoolExecutor}
 * Timer是单个线程定时,ScheduledThreadPoolExecutor可以是多线程的
 * schedule(Runnable runnable,  long delay, TimeUnit unit) 延迟指定时间且只执行一次
 * scheduleAtFixedRate(Runnable command,long initialDelay,long period,TimeUnit unit) 延迟指定时间循环执行,前一次开始和后一次开始间隔固定
 * scheduleWithFixedDelay(Runnable command,long initialDelay,long delay,TimeUnit unit); 延迟指定时间循环执行,前一次结束和后一次开始间隔固定
 * 如果定时任务出现异常，异常会被内部吞噬且定时停止
 * scheduledFuture.isCancelled()判断定时任务是否被取消
 * scheduledFuture.isDone()判断定时任务是否结束
 *
 */

@Slf4j
public class ScheduleThreadPoolExecutorDemo1 extends ScheduledThreadPoolExecutor {

    public ScheduleThreadPoolExecutorDemo1(int corePoolSize) {
        super(corePoolSize);
    }

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);
        //延迟指定时间执行一次
        scheduledExecutor.schedule(() -> {
            log.info("schedule开始执行时间为{}", Instant.now().toEpochMilli());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("schedule结束执行时间为{}", Instant.now().toEpochMilli());
        },5,TimeUnit.SECONDS);

        //延迟指定时间,并指定定时任务开始的时间间隔,此处的时间间隔指的是任务开始和下次任务开始的间隔
        scheduledExecutor.scheduleAtFixedRate(() -> {
            log.info("scheduleAtFixedRate开始执行时间为{}", Instant.now().toEpochMilli());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("scheduleAtFixedRate结束执行时间为{}", Instant.now().toEpochMilli());
        },5,5,TimeUnit.SECONDS);

        //延迟指定时间,并指定定时任务开始的时间间隔,此处的时间间隔指的是任务开始和上次任务结束的间隔
        ScheduledFuture<?> scheduledFuture = scheduledExecutor.scheduleWithFixedDelay(() -> {
            log.info("scheduleWithFixedDelay开始执行时间为{}", Instant.now().toEpochMilli());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("scheduleWithFixedDelay结束执行时间为{}", Instant.now().toEpochMilli());
        }, 5, 5, TimeUnit.SECONDS);
        log.info("主线程执行时间为{}", Instant.now().toEpochMilli());

        scheduledFuture.cancel(false);
        TimeUnit.SECONDS.sleep(10);
        log.info("scheduledFuture.isCancelled = {}",scheduledFuture.isCancelled());
        log.info("scheduledFuture.isDone()={}",scheduledFuture.isDone());
        scheduledExecutor.shutdown();
    }
}
