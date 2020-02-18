package com.beiming.thread.volatiles;

/**
 * volatile实例
 * 说明：
 *  新建线程中run方法中必须个空的死循环方法,作用是没有任何操作的话不会去主存中更新数据
 *  没有加volatile修饰时,新线程会进入死循环
 */

public class VolatileDemo {

    public static boolean flag = true;

    public static class MyThread extends Thread{

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("线程" + this.getName() + "进来了");
            while (flag){
                ;
            }
            System.out.println("线程" + this.getName() + "结束了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new MyThread("t1");
        t1.start();
        Thread.sleep(1000); //此处睡眠是因为启动新线程是需要时间,这样新线程读取的是改过后的值
        flag = false;
    }
}