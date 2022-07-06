package com.example._16.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class Main {

    private static final Map<String, Object> map = new HashMap<>();

    private static final CyclicBarrier barrier = new CyclicBarrier(4);

    public static void main(String[] args) {
        IntStream.range(0, 4)
                .parallel()
                .forEach(i -> {
                    try {
                        test(String.class);
                    } catch (InstantiationException
                             | IllegalAccessException
                             | BrokenBarrierException
                             | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static <T> T test(Class<T> clazz) throws InstantiationException, IllegalAccessException, BrokenBarrierException, InterruptedException {
        String key = clazz.toString();
        Object instance = map.get(key);
        barrier.await();
        synchronized (clazz) {
            if (instance == null) {
                instance = clazz.newInstance();
                map.put(key, instance);
                System.out.println("init end");
            }
        }
        return clazz.cast(instance);
    }
}
