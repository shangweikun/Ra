package com.example._7.demo;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class LFUCache {

    public LFUCache(int capacity) {
        this.size = capacity;
        this.map = new HashMap<>(
                1 << (Integer.bitCount(size) + 1),
                0.85F);
    }

    public Integer get(int key) {

        if (size == 0) {
            return -1;
        }

        if (map.containsKey(key)) {
            Value value = map.get(key);
            queue.remove(value.key);
            value.key.useCount++;
            value.key.timeVersion = aLong.getAndAdd(1L);
            queue.add(value.key);
            return value.value;
        }
        return -1;
    }

    public void put(int key, int element) {

        if (size == 0) {
            return;
        }

        if (map.containsKey(key)) {
            Value value = map.get(key);
            queue.remove(value.key);
            value.key.useCount++;
            value.key.timeVersion = aLong.getAndAdd(1L);
            queue.add(value.key);
            map.put(key, new Value(value.key, element));
        } else if (size <= map.size()) {
            Key target = queue.poll();
            assert target != null;
            map.remove(target.key);
            Key newKey = new Key(key);
            queue.add(newKey);
            map.put(key, new Value(newKey, element));
        } else {
            Key newKey = new Key(key);
            queue.add(newKey);
            map.put(key, new Value(newKey, element));
        }
    }

    private final int size;
    private final PriorityQueue<Key> queue = new PriorityQueue<>();
    private final HashMap<Integer, Value> map;
    private static final AtomicLong aLong = new AtomicLong();

    private static class Value {
        final Key key;
        final Integer value;

        public Value(Key key, Integer value) {
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

    private static class Key implements Comparable<Key> {

        Integer key;
        int useCount = 1;
        long timeVersion = aLong.getAndAdd(1L);

        public Key(Integer key) {
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
