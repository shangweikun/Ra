package com.example._17.demo.blog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Answer {

    private static final String NAME = "1";

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Test {
        String name() default NAME;
    }

    @Test
    public static final String demo = null;

    @Test
    public static final String demo1 = null;

    @Test(name = NAME)
    public static final String demo2 = null;

    @Test(name = NAME)
    public static final String demo3 = null;

    public static void main(String[] args) throws NoSuchFieldException {

        Field field = Answer.class.getField("demo");
        Test annotation = field.getAnnotation(Test.class);
        System.out.println("result test0 : " + (annotation.name() == NAME));

        Field field1 = Answer.class.getField("demo1");
        Test annotation1 = field1.getAnnotation(Test.class);
        System.out.println("result test1 : " + (annotation1.name() == annotation.name()));

    }

    private static void test2() throws NoSuchFieldException {
        Field field2 = Answer.class.getField("demo2");
        Test annotation2 = field2.getAnnotation(Test.class);
        Field field3 = Answer.class.getField("demo3");
        Test annotation3 = field3.getAnnotation(Test.class);
        System.out.println("result test2 : " + (annotation2.name() == annotation3.name()));
    }

}
