package com.beiming.thread.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * {@link java.util.concurrent.AbstractExecutorService} 是对 {@link java.util.concurrent.Executor}的抽象实现
 * {@link ThreadPoolExecutor}是对 {@link java.util.concurrent.AbstractExecutorService}的具体实现
 */

public class ThreadPoolExecutorDemo1 extends ThreadPoolExecutor {

    public ThreadPoolExecutorDemo1(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }


}
