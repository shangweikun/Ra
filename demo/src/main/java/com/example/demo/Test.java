package com.example.demo;

public class Test {

    public static class Sub {
        String string = "1";
    }

    public class Sub1 {
        String string = "1";
    }

    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        Sub1 sub1_1 = test1.new Sub1();
        Sub1 sub1_2 = test2.new Sub1();
        System.out.println(sub1_1.getClass().getName());
        System.out.println(sub1_1.getClass().hashCode());
        System.out.println(sub1_2.getClass().getName());
        System.out.println(sub1_2.getClass().hashCode());
//        System.out.println(sub1_1.getClass().equals(sub1_2.getClass())); always true
        System.out.println(test1.test1().equals(test2.test1()));
        System.out.println(test1.test().equals(test2.test()));
    }


    public static Class<?> test() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        };
        System.out.println(runnable.getClass().getName());
        System.out.println(runnable.getClass().hashCode());
        return runnable.getClass();
    }

    public Class<?> test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        };
        System.out.println(runnable.getClass().getName());
        System.out.println(runnable.getClass().hashCode());
        return runnable.getClass();
    }
}
