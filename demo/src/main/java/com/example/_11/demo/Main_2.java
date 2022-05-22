package com.example._11.demo;

public class Main_2 {

    private static final Object LOCK = new Object();

    private static class Test1 {

        static {
            System.out.println(Thread.currentThread().getName());
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                    //偶发无法被唤醒的情况
                    System.out.println("Test1 is run");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class Test2 {

        static {
            System.out.println(Thread.currentThread().getName());
            synchronized (LOCK) {
                System.out.println("Test2 is run");
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            Test1 test = new Test1();
            System.out.println("Hello Test1");
        });

        Thread thread2 = new Thread(() -> {
            Test2 test = new Test2();
            System.out.println("Hello Test2");
        });

        thread1.start();
        thread2.start();

        System.out.println("HelloWorld");

    }
}
