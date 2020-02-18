package com.beiming.thread.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *  队列(Queue)
 *  特点：先进先出(FIFO)
 *
 *  操作类型     抛出异常       返回特殊值       两种方法表现不同时            描述
 *   插入        add(e)         offer(e)        任何插入操作         插入一个元素
 *   移除        remove(e)      poll(e)         只有队列为空时       移除并返回队列头部元素
 *   检查        element()      peek()          只有队列为空时       返回队列头部元素但不删除
 */

@Slf4j
public class QueueDemo1 {

    static BlockingQueue queue = new ArrayBlockingQueue(10);

    public static void main(String[] args) {

        for(int i = 0; i < 10; i++){
            queue.add(i);
        }

        log.info("{}", queue.poll());
        log.info("{}", queue.poll());
        log.info("{}", queue.poll());
        log.info("{}", queue.poll());
        log.info("{}", queue.poll());
        log.info("{}", queue.poll());

        queue.clear();

        for(int i = 0; i < 10; i++){
            queue.add(i);
        }

        List<Object> lists= new ArrayList<>();
        queue.drainTo(lists, 5);
        lists.forEach(x -> System.out.println(x));

        log.info("{}", queue.peek());
    }
}
