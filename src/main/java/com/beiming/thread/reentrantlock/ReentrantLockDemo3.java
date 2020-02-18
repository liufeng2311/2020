package com.beiming.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的基本使用(重入锁)
 * 代码通过上了两次锁,但是只释放了一次,所以其他线程无法获取锁
 * 可重复锁是通过计数器实现的
 * 该实例不能正常执行
 */

@Slf4j
public class ReentrantLockDemo3 {

    private int num;

    Lock lock1 = new ReentrantLock();

    public void add() {
        lock1.lock();
        lock1.lock();
        try {
            num++;
        } finally {
            //lock1.unlock();  此处只释放一次锁
            lock1.unlock();
        }
    }

    static class MyThread extends Thread {

        ReentrantLockDemo3 demo;

        public MyThread(String groupName, String name, ReentrantLockDemo3 demo) {
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
        ReentrantLockDemo3 demo = new ReentrantLockDemo3();
        Thread t1 = new MyThread("liufeng1","t1", demo);
        Thread t2 = new MyThread("liufeng2","t2", demo);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(demo.num);
    }
}
