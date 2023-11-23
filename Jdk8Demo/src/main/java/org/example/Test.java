package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test {

    private static boolean b;
    private static int a;

    private static void test() {

        new MyThread().start();
        new Thread(() -> {
            b = true;
            a = 100;
        }).start();
    }

    private static void test0() {
        new Thread(() -> {
            while (!b) {
            }
            System.out.println(a);
        }).start();
        new Thread(() -> {
            b = true;
            a = 100;
        }).start();
    }

    private static void test1() {

        CyclicBarrier barrier = new CyclicBarrier(2);
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

    public static void main(String[] args) {
        while (true){
            test1();

            b = false;
            a = 0;

            System.out.println(a);
        }
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (!b) {
            }
            System.out.println(a);
        }
    }


}
