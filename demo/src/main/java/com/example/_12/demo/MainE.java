package com.example._12.demo;

import java.util.List;

public class MainE {

    static {
        List<String> result = List.of("1", "2")
                .parallelStream()
                .map(MainE::test)
                .toList();
    }

    public static void main(String[] args) {
        System.out.println("end");
    }

    public static String test(String i) {
        System.out.println(Thread.currentThread().getName());
        return "s";
    }
}
