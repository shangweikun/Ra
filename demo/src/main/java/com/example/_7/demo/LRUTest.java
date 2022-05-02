package com.example._7.demo;

public class LRUTest {

    public static void main(String[] args) {
        LRUCache<String,Integer> cache = new LRUCache<>(5);
        cache.put("1",1);
        cache.put("2",2);
        cache.put("3",3);
        cache.put("4",4);
        cache.put("5",5);
        cache.get("1");
        cache.put("6",6);

        System.out.println(cache);
    }
}
