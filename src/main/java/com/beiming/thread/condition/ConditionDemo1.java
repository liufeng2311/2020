package com.beiming.thread.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间的等待与唤醒
 */

public class ConditionDemo1 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    static class T1 extends Thread {

        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + "准备获取锁");
            lock.lock();
            try {
                System.out.println(this.getName() + "获取锁成功");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(this.getName() + "释放锁成功");
            }
        }
    }


    static class T2 extends Thread {

        public T2(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + "准备获取锁");
            lock.lock();
            try {
                System.out.println(this.getName() + "获取锁成功");
                condition.signal();
                System.out.println(this.getName() + "signal");
            } finally {
                lock.unlock();
                System.out.println(this.getName() + "释放锁成功");

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("t1");
        T2 t2 = new T2("t2");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t2.start();
    }
}
