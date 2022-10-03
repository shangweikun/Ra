package com.swk.example._1.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class App1 {

    private static int a;
    private static boolean b;

    private static CyclicBarrier barrier = new CyclicBarrier(2);

    public static void main(String[] args) {



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

    }
}
