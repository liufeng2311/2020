package com.beiming.thread.reentrantlock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁演示
 */

@Slf4j
public class ReentrantLockDemo6 {

    public static List<Boolean> flag =new ArrayList<>(2);

    static {
        flag.add(0, false);
        flag.add(1, true);
    }

    public static class T extends Thread {

        ReentrantLock lock;

        public T(String name, ReentrantLock lock) {
            super(name);
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    log.info("{}获取锁", getName());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            log.info("{}", flag.get(i) ? "公平锁演示" : "非公平锁演示");
            ReentrantLock lock = new ReentrantLock(flag.get(i)); //非公平锁
            T t1 = new T("t1", lock);
            T t2 = new T("t2", lock);
            T t3 = new T("t3", lock);
            t1.start();
            t2.start();
            t3.start();
            t1.join();
            t2.join();
            t3.join();
        }
    }
}
