package org.swk;

public class App1 {


    private static boolean b;
    private static int a;


    public static void main(String[] args) throws InterruptedException {


        Thread.sleep(20000L);

        System.out.println("begin");

        while (true) {

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

            while (thread0.isAlive() || thread1.isAlive()) ;

            synchronized (App1.class) {
                b = false;
                a = 0;
            }
        }
    }

}
