package com.beiming.thread.executor;

import java.util.concurrent.Executors;

/**
 * Executors是工具类,提供了创建线程池的方法
 */

public class ExecutorsDemo1 {

    public static void main(String[] args) {
        Executors.newSingleThreadExecutor(); //采用LinkedBlockingQueue队列
        Executors.newFixedThreadPool(10); //采用LinkedBlockingQueue队列
        Executors.newCachedThreadPool(); //采用SynchronousQueue队列
        Executors.newScheduledThreadPool(10); //采用LinkedBlockingQueue队列

    }
}
