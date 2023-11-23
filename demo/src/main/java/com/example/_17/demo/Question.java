package com.example._17.demo;

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

        String name() default name;
    }

    @Test
    public final String demo = "123";

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.identityHashCode(Test.name);

        Field field = Question.class.getField("demo");
        Field field1 = Question.class.getField("demo");
        Test annotation = field.getAnnotation(Test.class);
        Test annotation1 = field1.getAnnotation(Test.class);

        System.identityHashCode(annotation.name());
        System.identityHashCode(annotation1.name());

    }
}
