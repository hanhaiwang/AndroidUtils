package com.hanhaiwang.androidutil.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池工具类
 */
public class ThreadPoolUtil {
    //线程池核心线程数
    private static int CORE_POOL_SIZE = 5;
    //线程池最大线程数
    private static int MAX_POOL_SIZE = 100;
    //额外线程空状态生存时间
    private static int KEEP_ALIVE_TIME = 30;
    //阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。
    private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);
    //线程池
    private static ThreadPoolExecutor threadPool;
    //异步线程池
    private static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
    //UI handler
    private static Handler handlerUI;

    private ThreadPoolUtil(){}

    //线程工厂
    private static ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "thread_pool_fitness_" + integer.getAndIncrement());
        }
    };

    private synchronized static void initThreadPoolIfNecessary(){
        if(threadPool == null) {
            threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS, workQueue, threadFactory);
        }
    }

    private synchronized static void initScheduledThreadPoolIfNecessary(){
        if(scheduledThreadPoolExecutor == null) {
            scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, threadFactory);
        }
    }

    private synchronized static void initUIHandlerIfNecessary(){
        if(handlerUI == null){
            handlerUI = new Handler(Looper.getMainLooper());
        }
    }

    public static void post(Runnable runnable){
        initThreadPoolIfNecessary();
        threadPool.execute(runnable);
    }

    public static void postDelayed(Runnable runnable, long timeMiles){
        initScheduledThreadPoolIfNecessary();
        scheduledThreadPoolExecutor.schedule(runnable,timeMiles, TimeUnit.MILLISECONDS);
    }

    public static void postUI(Runnable runnable){
        initUIHandlerIfNecessary();
        handlerUI.post(runnable);
    }

    public static void postUIDelayed(Runnable runnable, long timeMiles){
        initUIHandlerIfNecessary();
        handlerUI.postDelayed(runnable,timeMiles);
    }
}

