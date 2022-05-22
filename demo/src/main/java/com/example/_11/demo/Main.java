package com.example._11.demo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //error
    static {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("1");
        list1.add("2");

        list2.add("1");

        list1.stream()
                .parallel()
                .map(i1->list2.stream().filter(i1::equals).findFirst().orElse(""))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        System.out.println("Hello World");

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("1");
        list1.add("2");

        list2.add("1");

        list1.stream()
                .parallel()
                .map(i1->list2.stream().filter(i1::equals).findFirst().orElse(""))
                .forEach(System.out::println);
    }
}
