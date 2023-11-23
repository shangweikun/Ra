package com.example._28.demo;

import java.io.IOException;
import java.io.InputStream;

public class App0 {

    public static void main(String[] args) throws
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                String targetName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream inputStream = getClass().getResourceAsStream(targetName);
                if (inputStream == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object obj = myClassLoader.loadClass("com.example._28.demo.A").newInstance();
        Object obj2 = App0.class.getClassLoader().loadClass("com.example._28.demo.A").newInstance();

        System.out.println((obj instanceof com.example._28.demo.A));
        System.out.println((obj2 instanceof com.example._28.demo.A));

    }
}
