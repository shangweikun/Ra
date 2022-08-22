package com.example._17.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Question1 {

    private static final String name = "123";

    private static final String name1 = "123";

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Test {

        String name() default name;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Test1 {

        String name() default name;
    }


    @Test
    public static final String demo = null;

    @Test1
    public static final String demo1 = null;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field field = Question1.class.getField("demo");
        Test annotation = field.getAnnotation(Test.class);
        String target = annotation.name();

        Field field1 = Question1.class.getField("demo1");
        Test1 annotation1 = field1.getAnnotation(Test1.class);
        String target1 = annotation1.name();

        System.out.println(target == target1);

        target = name;
        target1 = name;

        System.out.println(target == target1);

        System.out.println(name == name1);

    }
}
