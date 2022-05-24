package com.example._11.demo;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_5 {

//    private static final String test = "test - ";

    //error
    static {
        Stream.of(1, 2).parallel()
                .parallel()
                .peek(i -> System.out.println(Thread.currentThread().getName()))
                .map(String::valueOf)
                .forEach(System.out::println);

        System.out.println("end");
    }

    public static void main(String[] args) {

    }

    public void test() {
        String a = "";
        Consumer<String> c = s->System.out.println(s);
    }

}
