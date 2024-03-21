package com.swk.demo7.classloaders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadClassData(name);
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Could not load class " + name, e);
        }
    }

    private byte[] loadClassData(String name) throws IOException {
        // This is a simple example, in a real scenario you would fetch the class data from a file,
        // network, or other source based on the class name.
        InputStream stream = getClass().getClassLoader().getResourceAsStream(
                name.replace('.', '/') + ".class"
        );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int bufferSize = 4096;
        byte[] buffer = new byte[bufferSize];
        int bytesNumRead;
        while ((bytesNumRead = stream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesNumRead);
        }
        return baos.toByteArray();
    }
}
