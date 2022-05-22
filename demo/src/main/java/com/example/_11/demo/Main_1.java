package com.example._11.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main_1 {

    private static final CyclicBarrier barrier = new CyclicBarrier(2);

    private static class Test {

        static {
            System.out.println(Thread.currentThread().getName());
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            Test test = new Test();
            System.out.println("Hello Test1");
        });

        Thread thread2 = new Thread(() -> {
            Test test = new Test();
            System.out.println("Hello Test2");
        });

        thread1.start();
        thread2.start();

        System.out.println("HelloWorld");
    }
}
