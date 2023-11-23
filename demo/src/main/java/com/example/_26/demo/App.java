package com.example._26.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class App {

    private static final CyclicBarrier barrier = new CyclicBarrier(2);

    public static void main(String[] args) {

        Thread thread = new Thread(Demo::hello);
        Thread thread0 = new Thread(Demo::world);

        thread.start();
        thread0.start();

    }


    private static class Demo {

        public static synchronized void hello() {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hello");
        }

        public static synchronized void world() {
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
            System.out.println("world");
        }

    }
}
