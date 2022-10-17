package com.example._28.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class App2 {

    public static void main1(String[] args) {

        App2 app = new App2();
        Class clazz = App2.class;

        clazz.getFields();
        clazz.getDeclaredFields();
    }

    public static void test() {
        int count = 0;
        List<int[]> cache = new ArrayList<>();
        while (true) {
            System.out.println(count++);
            cache.add(new int[]{1});
        }
    }

    public static void test1() {

        int count = 0;
        List<Integer[]> cache = new ArrayList<>();
        while (true) {
            System.out.println(count++);
            cache.add(new Integer[]{1});
        }
    }

    public static void main(String[] args) {
        test();
    }


    public static class Question {

        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.FIELD)
        @interface Test {

            String name = "123";

            String name() default name;
        }

        @Test
        public final String demo = "123";

        public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

            Field field = Question.class.getField("demo");
            Test annotation = field.getAnnotation(Test.class);
            System.out.println(annotation.name() == Test.name);
        }


        private static class Demo1 {

            public static void main(String[] args) {
                List<String> tmp = new ArrayList<>(2);
                tmp.add("1");
                List tmp1 = tmp;
                tmp1.add(1);
                System.out.println(tmp1 == tmp);

                System.out.println("--------------");
                tmp1.forEach(System.out::println);
                for (Object o : tmp1) {
                    System.out.println(o.getClass());
                }
                System.out.println("--------------");
                tmp.forEach(System.out::println);
            }
        }
    }
}
