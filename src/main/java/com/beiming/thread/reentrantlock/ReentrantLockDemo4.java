package com.beiming.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的基本使用(公平锁和非公平锁)
 * ReentrantLock默认是非公平锁,随机的
 * 通过设置参数为true设置为公平锁,两个线程交替执行
 */

@Slf4j
public class ReentrantLockDemo4 {

    private int num;

    Lock lock1 = new ReentrantLock(true);

    public void add() {
        lock1.lock();
        try {
            num++;
            log.info("当前线程 = {}", Thread.currentThread().getName());
        } finally {
            lock1.unlock();
        }
    }

    static class MyThread extends Thread {

        ReentrantLockDemo4 demo;

        public MyThread(String groupName, String name, ReentrantLockDemo4 demo) {
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
        ReentrantLockDemo4 demo = new ReentrantLockDemo4();
        Thread t1 = new MyThread("liufeng1","t1", demo);
        Thread t2 = new MyThread("liufeng2","t2", demo);
        t2.start();
        t1.start();
    }
}
