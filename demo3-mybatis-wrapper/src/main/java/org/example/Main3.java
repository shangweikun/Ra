package org.example;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Main3 {

    public static void main(String[] args) throws NoSuchMethodException {

        printTestInfo(org.example1._2.TestMethod.class);
        System.out.println("=======================================");
        printTestInfo(org.example._2.TestMethod.class);
    }

    private static void printTestInfo(Class<?> clazz) throws NoSuchMethodException {
        Method test = clazz.getMethod("test", String.class);
        for (Parameter parameter : test.getParameters()) {
            System.out.println(parameter.getName());
        }
    }
}
