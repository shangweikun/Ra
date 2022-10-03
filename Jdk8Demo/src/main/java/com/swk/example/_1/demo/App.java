package com.swk.example._1.demo;

public class App {

    private static boolean b;
    private static int a;

    public static void main(String[] args) {

        Thread thread0 = new Thread(() -> {
            while (!b) {

            }
            System.out.println(a);
        });

        Thread thread1 = new Thread(() -> {
            b = true;
            a = 100;
        });

        thread0.start();
        thread1.start();
    }

}
