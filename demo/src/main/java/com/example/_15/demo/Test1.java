package com.example._15.demo;

import cn.hutool.core.collection.ListUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("begin ");

        Map<Integer, Map<String, List<Integer>>> dict = new HashMap<>();
        Random random = new Random();

        for (int i = 0; i < 5_000_000; i++) {
            Map<String, List<Integer>> info = new HashMap<>();
            info.put("open", ListUtil.toList(random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000)));
            info.put("calc", ListUtil.toList(random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000)));
            info.put("repay", ListUtil.toList(random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000)));
            info.put("calc all", ListUtil.toList(random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000),
                    random.nextInt(20_000_000)));
            dict.put(i, info);
        }

        System.out.println("end");
    }

    static class Test {
        public static void main(String[] args) {
            System.out.println(Integer.MAX_VALUE);
        }
    }
}
