package com.beiming.thread.juc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * {@link java.util.concurrent.ConcurrentHashMap}
 * 特点：
 *  K和V都不可以为null
 *  内部实现为红黑树
 */
public class ConcurrentHashMapDemo1 {

    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();
        Map map1 = new ConcurrentSkipListMap();
        map1.put(1,123);
    }
}
