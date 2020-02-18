package com.beiming.thread.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport作用是线程的等待与唤醒
 * 特点在于等待前执行唤醒也能时等待线程执行
 * 可以响应中断
 */

public class LockSupportDemo1 {

    static class T1 extends Thread{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "被唤醒继续执行");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("t1");
        t1.start();
        //TimeUnit.SECONDS.sleep(5);
        LockSupport.unpark(t1);
    }
}
