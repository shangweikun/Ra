package org.example;

import java.util.stream.IntStream;

public class Main {

    public static void test() throws InterruptedException {

        var vThread = Thread
                .ofVirtual()
                .unstarted(() -> System.out.println(Thread.currentThread()));

        vThread.start();
        vThread.join();

        System.out.println("Hello world!");
    }

    public static void main(String[] args) throws InterruptedException {

        var vThreads = IntStream.of(0, 10)
                .mapToObj(iterm -> Thread.ofVirtual().unstarted(() -> {

                    if (iterm == 0) {
                        System.out.println(Thread.currentThread());
                    }

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (iterm == 0) {
                        System.out.println(Thread.currentThread());
                    }
                }))
                .toList();

        vThreads.forEach(Thread::start);
        for (Thread vThread : vThreads) {
            vThread.join();
        }

    }
}