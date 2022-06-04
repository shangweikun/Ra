package com.example._11.demo;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.Stream;

public class Main_4 {

    private static final CyclicBarrier barrier = new CyclicBarrier(2);

    private static class Test {

        private final static String s = "123";

        static {
            Stream.of(0, 1, 2)
                    .parallel()
                    .forEach(i -> Stream.of(0, 1, 2, 3, 4, 5).forEach(
                            i2 -> System.out.println(i2 + i)
                    ));
            System.out.println("Hello Test");
        }
    }

    private static class Test1 {

        private final static String s = "123";

        static {
            Stream.of(0, 1, 2)
                    .parallel()
                    .forEach(i -> System.out.println(i + "" + s));
            System.out.println("Hello Test1");
        }
    }

    private static class Test2 {

        private final static String s = "123";

        static {
            Stream.of(0, 1, 2)
                    .parallel()
                    .forEach(System.out::println);
            System.out.println("Hello Test2");
        }
    }

    public static void main(String[] args) {

        new Thread(Test::new).start();
        new Thread(Test1::new).start();
        new Thread(Test2::new).start();

        System.out.println("HelloWorld");
    }
}
