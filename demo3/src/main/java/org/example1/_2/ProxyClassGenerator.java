package org.example1._2;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class ProxyClassGenerator {
    public static void main(String[] args) {
        MyInterface original = new MyInterfaceImpl();
        MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class<?>[]{MyInterface.class},
                new MyInvocationHandler(original)
        );

        // 获取代理类的字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(
                proxyInstance.getClass().getSimpleName(), new Class<?>[]{MyInterface.class}
        );

        // 将字节码写入文件
        try (FileOutputStream fos = new FileOutputStream(proxyInstance.getClass().getSimpleName() + ".class")) {
            fos.write(classFile);
            System.out.println("Proxy class file written successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
