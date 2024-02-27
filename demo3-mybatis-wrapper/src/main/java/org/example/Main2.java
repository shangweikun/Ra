package org.example;

import org.example1._1.BlogMapper;
import org.example1._2.TestMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Main2 {

    public static void main(String[] args) throws NoSuchMethodException {
        Method selectBlog = BlogMapper.class.getMethod(
                "selectBlog", String.class, String.class);

        for (Parameter parameter : selectBlog.getParameters()) {
            System.out.println(parameter.getName());
        }

        System.out.println("--------------------------------------");

        Method test = TestMethod.class.getMethod("test", String.class);
        for (Parameter parameter : test.getParameters()) {
            System.out.println(parameter.getName());
        }
    }
}
