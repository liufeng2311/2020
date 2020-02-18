package com.beiming.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的基本使用(多锁),每一个lock相当于一个个synchronized,互不影响
 * 先执行add,再执行add1,输出的都是100,因为add没有释放锁,相当于只有第一个获取到lock1的锁执行了
 * 先执行add1,再执行add,输出的是sum = 100,sum1 = 101,没有获取lock1锁的线程可以顺利执行add1，但是执行sum时进入了等待
 */

@Slf4j
public class ReentrantLockDemo2 {

    private int num;
    private int num1;

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public void add() {
        lock1.lock();
        try {
            num++;
        } finally {
            //lock1.unlock();
        }
    }

    public void add1() {
        lock2.lock();
        try {
            num1++;
        } finally {
            lock2.unlock();
        }
    }

    static class MyThread extends Thread {

        ReentrantLockDemo2 demo;

        public MyThread(String groupName, String name, ReentrantLockDemo2 demo) {
            super(new ThreadGroup(groupName), name);
            this.demo = demo;
        }

        @Override
        public void run() {
            for(int i = 0; i < 100; i++){
            demo.add();
            demo.add1();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo2 demo = new ReentrantLockDemo2();
        Thread t1 = new MyThread("liufeng1","t1", demo);
        Thread t2 = new MyThread("liufeng2","t2", demo);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(demo.num);
        System.out.println(demo.num1);
    }
}
