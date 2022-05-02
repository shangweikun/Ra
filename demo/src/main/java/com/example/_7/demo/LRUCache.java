package com.example._7.demo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, E> implements Cache<K, E> {

    private final LinkedHashMap<K, E> map;

    public LRUCache(int size) {
        this.map = new InnerLinkHashMap<>(size, 0.85f);
    }

    @Override
    public E get(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, E element) {
        map.put(key, element);
    }

    @Override
    public String toString() {
        return "LRUCache{" +
                "map=" + map +
                '}';
    }

    private static class InnerLinkHashMap<K, E> extends LinkedHashMap<K, E> {

        private final int size;

        private InnerLinkHashMap(int size, float loadFactor) {
            super(1 << (Integer.bitCount(size) + 1),
                    loadFactor, true);
            this.size = size;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, E> eldest) {
            return this.size() > this.size;
        }
    }
}
