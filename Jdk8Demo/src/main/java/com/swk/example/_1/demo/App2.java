package com.swk.example._1.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class App2 {

    private static int a;
    private static boolean b;

    private static CyclicBarrier barrier = new CyclicBarrier(2);

    public static void main(String[] args) {

        while (true) {

            test();

            synchronized (App2.class) {
                a = 0;
                b = false;
            }
        }
    }

    private static void test() {

        Thread thread0 = new Thread(() -> {

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

            while (!b) {

            }
            System.out.println(a);
        });

        Thread thread1 = new Thread(() -> {

            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }

            b = true;
            a = 100;
        });

        thread0.start();
        thread1.start();

        while (!b || thread0.isAlive() || thread1.isAlive()) ;

    }
}
