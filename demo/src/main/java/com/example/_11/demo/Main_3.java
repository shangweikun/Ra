package com.example._11.demo;

public class Main_3 {

    private static final Object Lock = new Object();

    private static class Test {

        static {
            System.out.println(Thread.currentThread().getName());
            synchronized (Lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + " in");
                    Lock.wait();
                    System.out.println(Thread.currentThread().getName() + " out");
                    Lock.notifyAll();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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

        while (thread1.isAlive() || thread2.isAlive()) {
            synchronized (Lock) {
                Lock.notifyAll();
            }
        }

    }
}
