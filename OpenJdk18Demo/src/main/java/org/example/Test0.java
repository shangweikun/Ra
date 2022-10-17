package org.example;

public class Test0 {

    private static class Inner {
        private static int a;
        private static boolean b;
    }

    public static void main(String[] args) {
        test();
    }

    private static void test() {

        Inner demo = new Inner() {
        };

        new MyThread(demo).start();
        new Thread(() -> {
            demo.b = true;
            demo.a = 100;
        }).start();
    }

    private static class MyThread extends Thread {

        private final Inner demo;

        public MyThread(Inner demo) {
            this.demo = demo;
        }

        @Override
        public void run() {
            while (!demo.b) {
            }
            System.out.println(demo.a);
        }
    }
}
