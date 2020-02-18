package com.beiming.thread.executor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * {@link ExecutorService}是对 {@link Executor}接口的拓展
 * 定义了关闭线程池的方法shutdown()和shutdownNow()
 * 定义了异步任务Future的相关方法
 */

public class ExecutorServiceDemo1 implements ExecutorService {

    /**
     * 线程池不再接受新的任务,但会继续执行已接收的任务,执行完后关闭
     */
    @Override
    public void shutdown() {

    }

    /**
     * 线程池会抛弃队列中任务,执行完后关闭
     * @return
     */

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void execute(Runnable command) {

    }
}
