package org.example;

public class Test1 {

    private static int a;
    private static boolean b;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!b) {
                }
                System.out.println(a);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                b = true;
                a = 100;
            }
        }).start();
    }
}
