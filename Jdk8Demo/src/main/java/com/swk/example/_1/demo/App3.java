package com.swk.example._1.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class App3 {

    private static int a = 0;

    private static final int count = 10;

    private static final CyclicBarrier barrier = new CyclicBarrier(count);

    public static void main(String[] args) {

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                for (int j = 0; j < 1000000; j++) {
                    a = a + 1;
                }
            });
            list.add(thread);
            thread.start();
        }

        while (list.stream().anyMatch(Thread::isAlive)) ;

        System.out.println(a);
    }

}
