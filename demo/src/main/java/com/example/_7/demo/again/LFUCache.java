package com.example._7.demo.again;

import com.example._7.demo.AtomicTimeStamp;
import com.example._7.demo.Cache;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class LFUCache<K, E> implements Cache<K, E> {

    private final TreeMap<Key<K>, E> map;
    private final int size;

    public LFUCache(int size) {
        this.map = new TreeMap<>();
        this.size = size;
    }

    @Override
    public E get(K key) {
        Optional<Map.Entry<Key<K>, E>> target;
        if ((target = hasKey(key)).isPresent()) {
            put(target.get().getKey(), target.get().getValue());
            return target.get().getValue();
        }
        return null;
    }

    private void put(Key<K> key, E value) {
        map.remove(key);
        key.useCount++;
        key.timeStamp = AtomicTimeStamp.getTimeStamp();
        map.put(key, value);
    }

    @Override
    public void put(K key, E element) {
        Optional<Map.Entry<Key<K>, E>> target = hasKey(key);
        if (size <= map.size() && target.isEmpty()) {
            removeKeyByOrder();
            map.put(new Key<>(key), element);
        }
        /*
        containsKey方法会通过compareTo来确认Key是否一致
        由于run状态下，timeStamp的时间戳差异过小;
        导致Key不同，但通过getEntryUsingComparator（CompareTo）方法却能获得一个相同的Entry，造成错误
         */
        else if (target.isPresent()) {
            Key<K> todo = target.get().getKey();
            map.remove(todo);
            todo.useCount++;
            todo.timeStamp = AtomicTimeStamp.getTimeStamp();
            map.put(todo, element);
        } else {
            map.put(new Key<>(key), element);
        }
    }

    private Optional<Map.Entry<Key<K>, E>> hasKey(K key) {
        return map.entrySet()
                .parallelStream()
                .filter(i -> i.getKey().key.equals(key))
                .findFirst();
    }

    @Override
    public String toString() {
        return "LFUCache0{" +
                "map=" + map +
                ", size=" + size +
                '}';
    }

    private void removeKeyByOrder() {
        map.remove(map.pollFirstEntry().getKey());
    }

    private static class Key<K> implements Comparable<Key<K>> {

        public Key(K key) {
            this.key = key;
            this.useCount = 1;
            this.timeStamp = AtomicTimeStamp.getTimeStamp();
        }

        K key;
        int useCount;
        long timeStamp;

        @Override
        public int compareTo(@NotNull Key<K> key) {
            int result;
            return (result = Integer.compare(this.useCount, key.useCount)) != 0 ?
                    result :
                    Long.compare(this.timeStamp, key.timeStamp);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key<?> key1 = (Key<?>) o;
            return key.equals(key1.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "Key{" +
                    "key=" + key +
                    ", useCount=" + useCount +
                    ", timeStamp=" + timeStamp +
                    '}';
        }
    }
}
