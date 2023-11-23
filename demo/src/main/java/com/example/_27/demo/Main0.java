package com.example._27.demo;

public class Main0 {

    private static int x = 0;
    private static volatile int y = 0;

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            y = 1;
            x = 1;
        });

        thread.start();
        int r1 = x;
        int r2 = y;

        //(1,0) 逻辑上存在
        System.out.println(r1 + "," + r2);
    }
}
