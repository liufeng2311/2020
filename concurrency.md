一、高并发设计的相关概念

    1.同步和异步
        同步：A方法调用B方法,等待B方法执行完之后,A方法才能继续执行
        异步：A方法调用B方法,A、B方法同时执行
    2.并发和并行
        并发：多个方法交替执行,某一时间点只有一个方法执行
        并行：多个方法同时执行,某一时间点多个方法同时执行
    3.临界区
        临界区表示一种每次只能被一个线程使用的公共资源。
    4.阻塞和非阻塞
        阻塞：线程因为得不到需要的资源而被挂起,直到得到需要的资源才能恢复
        非阻塞：线程不会因为资源不足而被挂起，而是尝试不断获取资源
    5.死锁、饥饿、活锁
        死锁：多线程相互占用对象需要的资源且不主动释放自己资源的现象(相互占用)
        饥饿：线程由于某种原因一直无法获取自己想要资源而无法执行的情况(竞争力低导致无法获取资源)
        活锁：主动将资源释放给他人使用，那么久会导致资源不断地在两个线程间跳动，而没有一个线程可以同时拿到所有资源正常执行
二、并发级别

    1.阻塞
        一个线程是阻塞的，那么在其他线程释放资源之前，当前线程无法继续执行。
        当我们使用synchronized关键字或者重入锁时，我们得到的就是阻塞的线程。
    2.无饥饿
        对应的公平锁,保证了先到先得,防止出现低优先级的一直得不到执行
    3.无障碍
        一种非阻塞调度，采用乐观锁进行回滚,
    4.无锁
        无锁的并行都是无障碍的。
        在无锁的情况下，所有的线程都能尝试对临界区进行访问，但不同的是，无锁的并发保证必然有一个线程能够在有限步内完成操作离开临界区
        一般的实现时通过一个死循环去获取临界资源,这样总有一个线程会成功
    5.无等待
        无锁只要求有一个线程可以在有限步内完成操作，而无等待则在无锁的基础上更进一步扩展。
        它要求所有线程都必须在有限步内完成,即循环的次数是有限的
三、Java内存模型(JMM)的相关概念
    
    1.原子性
        定义：一系列操作要么全部执行,要么全部执行。
        实现：锁机制、无锁CAS机制
    2.可见性
        定义：可见性是值一个线程对共享变量的修改，对于另一个线程来说是否是可以看到的。每一线程都有自己的工作副本，此时主存里的值可能已被修改
        实现：volatile、锁机制
    3.有序性
        定义：有序性指的是程序按照代码的先后顺序执行。为了性能优化，编译器和处理器会进行指令重排序。
        实例：双重检验锁定创建单例模式,由于编译期的重排序，可能会导致错误
        实现：
四、进程和线程

    1.进程
        定义：程序是指令、数据及其组织形式的描述，进程是程序的实体。是资源分配的最小单位
    2.线程
        定义：资源调度的最小单位
五、线程的基本操作

    1.创建线程的两种方式
        1.Thread
        2.Runnable
    2.线程中断
    3.wait()和notify()
    4.suspend()和resume()
    5.join()和yeild()
    
六、volatile关键字

    JVM中有一个主内存,每个线程都有自己的工作线程,工作线程中保存了从主板内存中读取的变量,因此一个线程中修改的变量可能在别的线程中不能及时读取到
    volatile关键字的作用就是修改的值立刻刷新进主存，每次使用该属性时都从主存中读取最新值
七、线程组

    线程组是一个属性结构,最高级为system线程组,方便我们统一管理线程。
八、用户线程和守护线程

九、synchronized关键字

十、线程中断

十一、ReentrantLock
    
    简介:ReentrantLock是Lock的默认实现类,1.5的新特性.
    特性:
        可重入锁：
        可中断锁：
        公平锁：
        非公平锁：
        可实现多个锁：
    示例：
        ReentrantLockDemo1: ReentrantLock的单锁演示
        ReentrantLockDemo2: ReentrantLock的多锁演示
        ReentrantLockDemo3: ReentrantLock是可重入锁演示
        ReentrantLockDemo4: ReentrantLock公平锁和非公平锁演示
        ReentrantLockDemo5: ReentrantLock是可中断锁演示
        ReentrantLockDemo6: ReentrantLock公平锁和非公平锁演示
十二、Condition

    简介:Lock框架中的等待唤醒机制,由ReentrantLock创建
    特性：
        支持多个等待队列：
    示例：
        ConditionDemo1：Condition等待与唤醒
        ConditionDemo2：Condition中断响应与等待超时唤醒
十三、LockSupport
    
    简介：用于线程的等待与唤醒
    示例：
十四、Semaphore

十五、CountDownLatch

    简介：等待多线程完成的工具类,用于取代join()
    示例：
十六、CyclicBarrier

    简介：等待达到指定数量线程时才开始运行,当有一个打破规则后,之前的都进行中断操作得以执行,之后的直接执行
十七、线程池

    简介：jdk中具体实现类为ThreadPoolExecutor
    
    核心参数：
    corePoolSize
    maximumPoolSize
    keepAliveTime
    Unit
    workQueue
    threadFactory
    handler
    
    执行逻辑：
        1.线程池中运行的线程数小于corePoolSize时,创建新的线程处理任务,若不满足,执行下一步
        2.若队列不满添加任务至workQueue队列,若不满足,执行下一步
        3.断线程池中运行的线程数是否小于maximumPoolSize,是则新增线程处理当前传入的任务,否将任务传递给handler对象rejectedExecution方法处理
        
    常用的阻塞队列：  
         ArrayBlockingQueue：基于数组的有界阻塞队列
         LinkedBlockingQueue：基于列表的阻塞队列,newFixedThreadPool使用该队列
         SynchronousQueue ：不存储元素的阻塞队列,只有当线程调用取时才会插入,否则一直阻塞,newCachedThreadPool使用该队列
         PriorityBlockingQueue:根据线程优先级排序
         
    自定义创建线程工厂：
        实现ThreadFactory接口
    
    常见拒绝策略：实现RejectedExecutionHandler接口
        AbortPolicy：直接抛出异常
        CallerRunsPolicy：在当前调用者的线程中运行任务
        DiscardOldestPolicy：丢弃最老的一个任务
        DiscardPolicy：不处理,直接丢弃
        
    线程池关闭的方法
    示例:
        ThreadPoolExecutorDemo1:线程池的简单使用
        CachedThreadPoolDemo1:SynchronousQueue队列演示
        
十八、Executor框架

    Executor：框架核心类,示例：ExecutorDemo1
    ExecutorService：Executor功能拓展,示例：ExecutorServiceDemo1
    ThreadPoolExecutor：ExecutorService的实现,示例：ThreadPoolExecutorDemo1
    ScheduleThreadPoolExecutor： ThreadPoolExecutor的扩展：示例：ScheduleThreadPoolExecutorDemo1
    Executors：工具类,用于创建线程池
    Future:接口定义了操作异步异步任务执行一些方法
    Callable:接口中定义了需要有返回的任务需要实现的方法
    FutureTask:表示带有返回值的任务,是Future的实现
    CompletionService：ExecutorCompletionService是实现类，相当于一个执行任务的服务，通过submit丢任务给这个服务，服务内部去执行任务，可以通过服务提供的一些方法获取服务中已经完成的任务
    
十九、高并发计数器
    
    HighConcurrencyCountDemo1:synchronized     关键字
    HighConcurrencyCountDemo2:AtomicLong       CAS实现
    HighConcurrencyCountDemo3:LongAdder        高性能AtomicLong
    HighConcurrencyCountDemo4:LongAccumulator  LongAdder升级版
    
二十、获取线程执行结果的六种方法

    ResultDemo1：join方式获取结果
    ResultDemo2：countDownLatch方式获取结果
    ResultDemo3：executorService方式获取结果
    ResultDemo4：futureTask方式获取结果
    ResultDemo5：completableFuture方式获取结果,CompletableFuture.supplyAsync()

二十一、JUC常见的集合

    1.Map的特点采用key-value键值对映射的方式进行存储,key唯一
    
     