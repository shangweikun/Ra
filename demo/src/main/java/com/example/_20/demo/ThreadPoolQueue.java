package com.example._20.demo;

import scala.tools.nsc.Global;

import java.util.concurrent.*;

public class ThreadPoolQueue {

    public static final ThreadPoolExecutor executor =
            new ThreadPoolExecutor(5, 5,
                    5L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(100));

    private static final long timeout = 10L;

    public static <T> QueueFuture<T> submit(Callable<T> callable, String seqNo) {

        return new QueueFuture<>(executor.submit(callable), timeout, seqNo);
    }
}
