package com.example._7.demo.again;

import com.example._7.demo.again.LFUCache0;

public class LFUTest0 {


    public static void main(String[] args) {
        LFUCache0<String,Integer> cache = new LFUCache0<>(5);
        cache.put("11",11);
        cache.put("22",22);
        cache.put("33",33);
        cache.put("44",44);
        cache.put("55",55);
        System.out.println(cache);
        cache.put("51",51);
        System.out.println(cache);
        cache.get("22");
        cache.put("6",6);
        System.out.println(cache);
    }
}
