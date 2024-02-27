package org.example1._3;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterNames {

    static class MyClass {
        public void myMethod(String greeting, int times) {
            // ...
        }
    }
    public static void main(String[] args) throws Exception {
        Method method = MyClass.class.getMethod("myMethod", String.class, int.class);
        for (Parameter parameter : method.getParameters()) {
            System.out.println(parameter.getName());
        }
    }
}