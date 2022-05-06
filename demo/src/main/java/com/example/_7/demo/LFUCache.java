package com.example._7.demo;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class LFUCache<K, E> implements Cache<K, E> {

    public LFUCache(int capacity) {
        this.size = capacity;
        this.map = new HashMap<>(
                1 << (Integer.bitCount(size) + 1),
                0.85F);
    }

    public E get(K key) {

        if (size == 0) {
            return null;
        }

        if (map.containsKey(key)) {
            Value<K, E> value = map.get(key);
            queue.remove(value.key);
            value.key.useCount++;
            value.key.timeVersion = aLong.getAndAdd(1L);
            queue.add(value.key);
            return value.value;
        }
        return null;
    }

    public void put(K key, E element) {

        if (size == 0) {
            return;
        }

        if (map.containsKey(key)) {
            Value<K, E> value = map.get(key);
            queue.remove(value.key);
            value.key.useCount++;
            value.key.timeVersion = aLong.getAndAdd(1L);
            queue.add(value.key);
            map.put(key, new Value<>(value.key, element));
        } else if (size <= map.size()) {
            Key<K> target = queue.poll();
            assert target != null;
            map.remove(target.key);
            Key<K> newKey = new Key<>(key);
            queue.add(newKey);
            map.put(key, new Value<>(newKey, element));
        } else {
            Key<K> newKey = new Key<>(key);
            queue.add(newKey);
            map.put(key, new Value<>(newKey, element));
        }
    }

    private final int size;
    private final PriorityQueue<Key<K>> queue = new PriorityQueue<>();
    private final HashMap<K, Value<K, E>> map;
    private static final AtomicLong aLong = new AtomicLong();

    private static class Value<K2, V2> {
        final Key<K2> key;
        final V2 value;

        public Value(Key<K2> key, V2 value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private static class Key<K1> implements Comparable<Key<K1>> {

        K1 key;
        int useCount = 1;
        long timeVersion = aLong.getAndAdd(1L);

        public Key(K1 key) {
            this.key = key;
        }

        @Override
        public int compareTo(Key o) {
            int result;
            return (result = Integer.compare(this.useCount, o.useCount)) != 0
                    ? result :
                    Long.compare(this.timeVersion, o.timeVersion);
        }

        @Override
        public String toString() {
            return "Key{" +
                    "key=" + key +
                    ", useCount=" + useCount +
                    ", timeVersion=" + timeVersion +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LFUCache{" +
                "size=" + size +
                ", queue=" + queue +
                ", map=" + map +
                '}';
    }
}
