package com.example._25.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class Main {

    private static class SingleDemo {

        private SingleDemo() {

        }

        private static final SingleDemo initDemo = new SingleDemo();
        private static SingleDemo demo = initDemo;

        private static SingleDemo getInstance() {
            SingleDemo tmp = SingleDemo.demo;
            if (tmp == initDemo) {
                synchronized (SingleDemo.class) {
                    tmp = SingleDemo.demo;
                    if (demo == initDemo) {
                        System.out.println("test");
                        SingleDemo.demo = new SingleDemo();
                    }
                }
            }
            return SingleDemo.demo;
        }

    }

    private static class SingleDemo2 {

        private SingleDemo2() {

        }

        private static SingleDemo2 demo = null;

        private static SingleDemo2 getInstance() {
            if (demo == null) {
                synchronized (SingleDemo2.class) {
                    if (demo == null) {
                        System.out.println("test");
                        SingleDemo2.demo = new SingleDemo2();
                    }
                }
            }
            return SingleDemo2.demo;
        }

    }

    private static final CyclicBarrier barrier = new CyclicBarrier(20);

    public static void main(String[] args) {

        IntStream.range(0, 20)
                .parallel()
                .forEach(i -> {

                    try {
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(
                            SingleDemo2.getInstance().hashCode() + "|"
                                    + Thread.currentThread().getName());
                });


    }
}
