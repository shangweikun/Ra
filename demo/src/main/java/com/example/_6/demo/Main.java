package com.example._6.demo;

import cn.hutool.core.map.MapBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MapBuilder.create(new HashMap<String,String[]>()).put("a",new String[]{"y", "z"}).put("b",new String[]{"x", "y", "z"}).put("c",new String[]{"y"}).build().entrySet().stream().sorted(Comparator.comparingInt(i -> i.getValue().length)).reduce(new HashMap<String, String>(), (n, e) -> {n.put(e.getKey(), Arrays.stream(e.getValue()).dropWhile(n::containsValue).findFirst().orElseThrow());return n;}, (m1, m2) -> m2).forEach((k, v) -> System.out.println(k + "-" + v));
    }
}
