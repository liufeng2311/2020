package com.beiming.thread.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间await()中断
 * boolean await(long time, TimeUnit unit) throws InterruptedException,超时时不触发异常
 * 返回false表示超时后继续执行,true表示超时前被唤醒
 */

public class ConditionDemo2 {

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
                System.out.println("中断标志：" + this.isInterrupted());
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
                boolean await = condition.await(20, TimeUnit.SECONDS);
                System.out.println(await);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(this.getName() + "释放锁成功1111");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================测试中断方法==================");
        T1 t1 = new T1("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(t1.getName() + "的中断标志1：" + t1.isInterrupted());
        t1.interrupt();
        System.out.println(t1.getName() + "的中断标志2：" + t1.isInterrupted());
        System.out.println("=================测试condition.await(20, TimeUnit.SECONDS)方法==================");
        T2 t2 = new T2("t2");
        t2.start();
        TimeUnit.SECONDS.sleep(5);
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
