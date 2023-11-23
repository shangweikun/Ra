package com.example._17.demo.blog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Question {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Test {

        String name = "123";

        String name() default Test.name;
    }

    static String get() {
        return Test.name;
    }

    @Test
    public final String demo = "123";

    @Test
    public final String demo3 = "123";

    @Test(name = "123")
    public final String demo4 = "123";

    @Test(name = Test.name)
    public final String demo5 = "123";

    @Test(name = Test.name)
    public final String demo6 = "123";

    private static String demo1 = Test.name;
    private static String demo2 = Test.name;


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field field = Question.class.getField("demo");
        Test annotation = field.getAnnotation(Test.class);
        System.out.println(annotation.name() == Test.name);
        System.out.println(demo1 == demo2);

        Field field1 = Question.class.getField("demo3");
        Test annotation1 = field1.getAnnotation(Test.class);
        System.out.println(annotation.name() == annotation1.name());

        Field field2 = Question.class.getField("demo4");
        Test annotation2 = field2.getAnnotation(Test.class);
        System.out.println("2 " + (annotation.name() == annotation2.name()));
        System.out.println("2 " + (Test.name == annotation2.name()));


        Field field3 = Question.class.getField("demo5");
        Test annotation3 = field3.getAnnotation(Test.class);
        System.out.println("3 " + (annotation.name() == annotation3.name()));
        System.out.println("3 " + (Test.name == annotation3.name()));

        Field field4 = Question.class.getField("demo6");
        Test annotation4 = field4.getAnnotation(Test.class);
        System.out.println("4 " + (annotation4.name() == annotation3.name()));
        System.out.println("4 " + (Test.name == annotation4.name()));

        System.out.println(get() == Test.name);
    }
}

