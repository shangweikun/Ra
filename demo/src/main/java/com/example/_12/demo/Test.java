package com.example._12.demo;

public class Test {

    public static void test() {
        System.out.println("test");
    }

    static {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.getClass().getName());
                Test.test();
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("end");
    }
}
