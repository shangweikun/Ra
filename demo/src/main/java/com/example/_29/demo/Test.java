package com.example._29.demo;

public class Test {

    private static boolean b;
    private static int a;

    private static void test() {
        new MyThread().start();
        new Thread(() -> {
            b = true;
            a = 100;
        }).start();
    }

    private static void test0() {
        new Thread(() -> {
            while (!b) {
            }
            System.out.println(a);
        }).start();
        new Thread(() -> {
            b = true;
            a = 100;
        }).start();
    }

    public static void main(String[] args) {
        test();
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (!b) {
            }
            System.out.println(a);
        }
    }


}
