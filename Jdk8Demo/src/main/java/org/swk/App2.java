package org.swk;

public class App2 {


    private static boolean b;
    private static int a;

    private static void test() {

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

    public static void main(String[] args) throws InterruptedException {


        Thread.sleep(30000L);

        System.out.println("begin");

        test();
    }

}
