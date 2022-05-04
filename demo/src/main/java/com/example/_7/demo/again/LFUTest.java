package com.example._7.demo.again;

public class LFUTest {

    public static void main(String[] args) {

        LFUCache<String, Integer> cache = new LFUCache<>(5);
        cache.put("1", 11);
        cache.put("2", 22);
        cache.put("3", 33);
        cache.put("4", 44);
        cache.put("555", 555);
        System.out.println(cache);
        cache.put("51", 51);
        System.out.println(cache);
        cache.get("2");
        System.out.println(cache);
        cache.put("666", 666);
        System.out.println(cache);
    }
}
