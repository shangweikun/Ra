package com.example._27.demo;

public class Main {

    private static int x = 0;
    private static volatile int y = 0;


    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            x = 1;
            y = 1;
        });

        thread.start();
        int r2 = y;
        int r1 = x;

        System.out.println(r1 + "," + r2);
    }
}
