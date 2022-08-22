package com.example._20.demo;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class QueueFuture<T> implements Future<T> {

    private final Future<?> future;

    private final long timeout;

    private final String seqNo;

    public QueueFuture(Future<?> future,
                       long timeout,
                       String seqNo) {
        this.future = future;
        this.timeout = timeout;
        this.seqNo = seqNo;
    }


    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCancelled() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDone() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get() {

        try {
            try {
                @SuppressWarnings("unchecked")
                T result = (T) future.get(timeout, TimeUnit.MILLISECONDS);
                return result;
            } catch (TimeoutException e) {

                if (future.cancel(true)) {
                    System.out.println(seqNo + " run timeout !!! [" + e.getMessage() + "]");
                    return null;
                } else {
                    @SuppressWarnings("unchecked")
                    T result = (T) future.get();
                    return result;
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            System.out.println(seqNo + " execute error !!! [" + e.getMessage() + "]");
            return null;
        }
    }

    @Override
    public T get(long timeout, @NotNull TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException();
    }
}