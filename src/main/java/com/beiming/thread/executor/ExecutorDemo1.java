package com.beiming.thread.executor;

        import java.util.concurrent.Executor;

/**
 * {@link Executor}是Executors的核心所在
 *  定义了一个执行方法,参数是实现 {@link Runnable}的类
 *
 */

public class ExecutorDemo1 implements Executor {

    @Override
    public void execute(Runnable command) {

    }
}
