package com.example._7.demo;


public class LFUTest {

    public static void main(String[] args) {
        /*
         ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
         [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
         */
        LFUCache<Integer, Integer> cache = new LFUCache<>(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

        System.out.println(cache);
    }
}
