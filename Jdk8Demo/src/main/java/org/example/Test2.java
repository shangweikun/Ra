package org.example;

public class Test2 {


    public static class Inner {
        private static int a;
        private static boolean b;
    }


    public static void main(String[] args) {

        while (true) {

            test();

            synchronized (Test2.Inner.class) {
                Inner.a = 0;
                Inner.b = false;
            }

        }
    }

    private static void test() {
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Inner.b) {
                }
                System.out.println(Inner.a);
            }
        }){
            private final Inner inner = new Inner();
        };

        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                Inner.b = true;
                Inner.a = 100;
            }
        }){
            private final Inner inner = new Inner();
        };

        thread0.start();
        thread1.start();

        while (thread0.isAlive() || thread1.isAlive()) ;
    }
}
