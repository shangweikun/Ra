package com.example._20.demo;

import cn.hutool.core.util.RandomUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

public class App {


    private static final Callable<String> callable = () -> "1";

    private static final Semaphore semaphore = new Semaphore(-6);

    private static final Runnable runnable = () -> {
        for (int i = 0; i < 100_000; i++) {
            QueueFuture<String> future =
                    ThreadPoolQueue.submit(callable,
                            Thread.currentThread().getName() + "_" + i);
            if (future.get() == null) {
                System.out.println(Thread.currentThread().getName() + "_" + i);
            }
        }
        System.out.println(Thread.currentThread().getName() + " end !");
        semaphore.release();
    };

    public static void main(String[] args) throws InterruptedException {


        Thread thread = new Thread(runnable);
        Thread thread0 = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        Thread thread4 = new Thread(runnable);
        Thread thread5 = new Thread(runnable);


        thread.start();
        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        semaphore.acquire();

        ThreadPoolQueue.executor.shutdown();
    }


    public static class Test {

        public static void main(String[] args) {
            ThreadPoolQueue.submit(() -> {
                try {

                    return true;
                } catch (Throwable t) {
                    return false;
                } finally {
                    // TODO: 17/08/2022 rollback resource
                }
            }, String.valueOf(RandomUtil.randomInt()));
        }
    }


}
