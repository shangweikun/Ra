package com.example._11.demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main_6 {

    private static String val = "1";

    private static final CyclicBarrier barrier = new CyclicBarrier(2);

    public static void test() {

        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        };

        System.out.println(run.getClass().getName());
        System.out.println(run.getClass().hashCode());
    }

    public Runnable test1(String a) {

        Runnable run = new Runnable() {

            //该匿名内部类是属于类的，

            //error 传入的局部变量不可以使用
//            private static final String running = a;
            private static final String running = " inner";

            static {
//                tryWait();
            }

            @Override
            public void run() {
                System.out.println(a + running);
            }
        };

        System.out.println(run.getClass().getName());
        System.out.println(run.getClass().hashCode());

        return run;
    }


    public Runnable test2(String a) {

        Runnable run = new Runnable() {

            //该匿名内部类是属于类的，

            //error 传入的局部变量不可以使用
            private static final String running = val;

            static {
//                tryWait();
            }

            @Override
            public void run() {
                System.out.println(a + running);
            }
        };

        System.out.println(run.getClass().getName());
        System.out.println(run.getClass().hashCode());

        return run;
    }

    private static void tryWait() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    private class Test3{
        private static String test = val;

        public Runnable test3(String a) {

            Runnable run = () -> System.out.println(a + test);

            System.out.println(run.getClass().getName());
            System.out.println(run.getClass().hashCode());

            return run;
        }
    }

    static class Test4{

        public Runnable test3(String a) {

            Runnable run = () -> System.out.println(a);

            System.out.println(run.getClass().getName());
            System.out.println(run.getClass().hashCode());

            return run;
        }
    }

    public static void main(String[] args) {
//
//        new Thread(new Main_6().test2("123 ")).start();
//        val = "2";
//        new Thread(new Main_6().test2("456 ")).start();

        Main_6 main1 = new Main_6();
        Main_6 main2 = new Main_6();

        new Thread(main1.new Test3().test3("123 ")).start();
        val = "2";
        new Thread(main2.new Test3().test3("456 ")).start();
    }

}

