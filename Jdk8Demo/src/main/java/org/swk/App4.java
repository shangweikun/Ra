package org.swk;

public class App4 {


    private static boolean b;
    private static int a;


    public static void main(String[] args) throws InterruptedException {


        Thread.sleep(20000L);

        System.out.println("begin");

        Thread thread0;
        Thread thread1;

        while (true) {

            thread0 = new Thread(() -> {
                while (!b) {
                }
                System.out.println(a);
            });
            thread1 = new Thread(() -> {
                b = true;
                a = 100;
            });
            thread0.start();
            thread1.start();

            while (!b || thread0.isAlive() || thread1.isAlive()) ;

            synchronized (App4.class) {
                b = false;
                a = 0;
            }
        }
    }

}
