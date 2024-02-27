package org.example1._2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;

public class ProxyMethodParamMain {

    public static void main(String[] args) throws NoSuchMethodException {
        MyInterface original = new MyInterfaceImpl();
        MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class<?>[]{MyInterface.class},
                new MyInvocationHandler(original)
        );

        Method performAction = proxyInstance.getClass().getMethod("performAction", String.class);
        Parameter[] parameters = performAction.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println(parameter.getName());
        }
    }
}
