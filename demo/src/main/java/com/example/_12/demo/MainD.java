package com.example._12.demo;

import java.util.List;

public class MainD {

    static {
        List<String> result = List.of(1, 2)
                .parallelStream()
                .map(i -> {
                    return "s" + i;
                })
                .toList();
    }

    public static void main(String[] args) {
        System.out.println("end");
    }
}
