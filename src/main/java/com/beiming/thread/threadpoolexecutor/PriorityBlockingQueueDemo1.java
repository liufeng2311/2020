package com.beiming.thread.threadpoolexecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * PriorityBlockingQueue示例
 * 使用该队列的任务必须实现排序方法
 */

@Slf4j
public class PriorityBlockingQueueDemo1 {

    static class Task implements Runnable,Comparable<Task>{

        private int i;
        private String name;

        public Task(int i, String name) {
            this.i = i;
            this.name = name;
        }

        @Override
        public void run() {
            log.info("{}处理了任务{}",Thread.currentThread().getName(),name);
        }

        @Override
        public int compareTo(Task o) {
            return Integer.compare(o.i,this.i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(1,
                1,
                60L,
                TimeUnit.SECONDS,
                new PriorityBlockingQueue<>());
        for(int i = 0; i < 10; i++){
            executor.execute(new Task(i, "任务"+i));
        }
        for(int i = 100; i >= 90; i--){
            executor.execute(new Task(i, "任务"+i));
        }
        executor.shutdown();
    }
}

