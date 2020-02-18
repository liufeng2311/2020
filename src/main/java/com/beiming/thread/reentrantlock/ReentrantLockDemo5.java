package com.beiming.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的基本使用(可中断锁)
 * lockInterruptibly()表示获取锁的过程中响应中断请求,interrupt()设置中断标志为true,响应时会触发InterruptedException,并设置中断标志为false
 * lock()不响应中断,等同于synchronized
 * lock()和unlock()必须同时出现,只执行lock()或者unlock()都会阻塞
 * isHeldByCurrentThread()方法判断该锁是否被当前线程持有,存在于ReentrantLock中,Lock中不存在该方法
 * tryLock()方法尝试获取锁,会立即返回一个状态,true表示获取成功,false表示获取失败,不会响应中断
 * tryLock(long timeout, TimeUnit unit)方法在指定时间内获取锁,会响应中断
 */

@Slf4j
public class ReentrantLockDemo5 {

    static ReentrantLock lock1 = new ReentrantLock(false);
    static ReentrantLock lock2 = new ReentrantLock(false);

    static class MyThread extends Thread {

        int lock;

        public MyThread(String name, int lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                if (lock == 1) {
                    lock1.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock2.lockInterruptibly();
                } else {
                    lock2.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(1);
                    lock1.lockInterruptibly();
                }
            } catch (InterruptedException e) {
                System.out.println("中断标志：" + this.isInterrupted());
            } finally {
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                } else {
                    lock2.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("t1", 1);
        Thread t2 = new MyThread("t2", 2);
        t2.start();
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        t2.interrupt();
    }
}
