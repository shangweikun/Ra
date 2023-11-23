package org.swk;

public class App {


    private static boolean b;
    private static int a;

    private static void test() {
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

    public static void main(String[] args) throws InterruptedException {


        Thread.sleep(30000L);

        System.out.println("begin");

        test();
    }

}
