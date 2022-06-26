package com.example._13.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.transform.impl.AddPropertyTransformer;
import org.objectweb.asm.Type;

public class Main {

    public static class Test {

    }

    public static void main(String[] args) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback((InvocationHandler) (o, method, objects) -> null);
        enhancer.generateClass(
                new AddPropertyTransformer(
                        new String[]{"name"}, new Type[]{Type.getType(String.class)}
                )
        );
    }
}
