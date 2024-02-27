package org.example1._1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GreetingInvocationHandler implements InvocationHandler {
    private final Object target;

    public GreetingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method call");
        Object result = method.invoke(target, args);
        System.out.println("After method call");
        return result;
    }
}
