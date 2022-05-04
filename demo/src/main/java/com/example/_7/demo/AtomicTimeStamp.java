package com.example._7.demo;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicTimeStamp {

    private static final AtomicLong lastLong = new AtomicLong(System.currentTimeMillis());

    public static long getTimeStamp() {
        long lastTimeStamp = lastLong.get();
        long newTimeStamp = getCurrentTimeDelay();
        long value = 0L;
        while (true) {
            value = lastLong.compareAndExchange(lastTimeStamp, newTimeStamp);
            if (value == lastTimeStamp) {
                break;
            }
            lastTimeStamp = lastLong.get();
            newTimeStamp = getCurrentTimeDelay();
        }
        return newTimeStamp;
    }

    private static long getCurrentTimeDelay() {
        while (true) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                continue;
            }
            return System.currentTimeMillis();
        }
    }

}
