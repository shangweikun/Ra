package com.example._32.demo;

import java.util.ArrayList;
import java.util.List;

public class AppMain {

    private static class Test {
        private class Inner {
        }
    }

    public static void main(String[] args) {
        List<Object> test = new ArrayList<>();

        while (true) {

            test.add(new Test().new Inner(){});

            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}