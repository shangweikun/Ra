package com.example._7.demo.ready;

import com.example._7.demo.Cache;
import com.example._7.demo.LFUCache;
import com.example._7.demo.LRUCache;

public class CacheFactory {

    public static <K, E> Cache<K, E> createLFUCache(int size) {
        return new LFUCache<>(size);
    }

    public static <K, E> Cache<K, E> createLRUCache(int size) {
        return new LRUCache<>(size);
    }
}
