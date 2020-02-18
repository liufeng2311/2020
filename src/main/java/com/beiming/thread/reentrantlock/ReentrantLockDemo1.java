package com.beiming.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的基本使用(单锁),相当于synchronized
 */

@Slf4j
public class ReentrantLockDemo1 {

    private int num;

    Lock lock = new ReentrantLock();

    public void  add() {
        lock.lock();
        try {
            log.info("当前线程为={},num的值={}", Thread.currentThread().getName(), num);
            log.info("当前线程的线程组为={}", Thread.currentThread().getThreadGroup().getName());
            num++;
        } finally {
            lock.unlock();
        }
    }

    static class MyThread extends Thread {

        ReentrantLockDemo1 demo;

        public MyThread(String groupName, String name, ReentrantLockDemo1 demo) {
            super(new ThreadGroup(groupName), name);
            this.demo = demo;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++){
            demo.add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo1 demo = new ReentrantLockDemo1();
        Thread t1 = new MyThread("liufeng1","t1", demo);
        Thread t2 = new MyThread("liufeng2","t2", demo);
        Thread t3 = new MyThread("liufeng3","t3", demo);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(demo.num);
    }
}
