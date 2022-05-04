package com.example._7.demo.again;

import com.example._7.demo.AtomicTimeStamp;
import com.example._7.demo.Cache;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class LFUCache0<K, E> implements Cache<K, E> {

    private final Object[] values;
    private int currentSize;

    public LFUCache0(int size) {
        this.values = new Object[size];
        this.currentSize = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(K key) {

        for (int i = 0; i < currentSize; i++) {
            if (key.equals(((Entry<K, E>) values[i]).key)) {
                ((Entry<?, ?>) values[i]).useCount++;
                ((Entry<?, ?>) values[i]).timeStamp = AtomicTimeStamp.getTimeStamp();
                return ((Entry<K, E>) values[i]).element;
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void put(K key, E element) {

        for (int i = 0; i < currentSize; i++) {
            if (key.equals(((Entry<K, E>) values[i]).key)) {
                ((Entry<K, E>) values[i]).element = element;
                ((Entry<?, ?>) values[i]).useCount++;
                ((Entry<?, ?>) values[i]).timeStamp = AtomicTimeStamp.getTimeStamp();
                ;
                return;
            }
        }

        if (currentSize < values.length) {
            values[currentSize] = new Entry<>(key, element);
            currentSize++;
        } else {
            Entry<K, E> entry = Arrays.stream(values)
                    .map(Entry.class::cast)
                    .min(Entry<K, E>::compareTo)
                    .orElseThrow();

            for (int i = 0; i < values.length; i++) {
                if (entry.equals(values[i])) {
                    values[i] = new Entry<>(key, element);
                    break;
                }
            }
        }


    }

    @Override
    public String toString() {
        return "LFUCache0{" +
                "values=" + Arrays.toString(values) +
                ", currentSize=" + currentSize +
                '}';
    }

    private static class Entry<K, E> implements Comparable<Entry<K, E>> {

        private Entry(K key, E element) {
            this.key = key;
            this.element = element;
            this.useCount = 1;
            this.timeStamp = AtomicTimeStamp.getTimeStamp();
        }

        K key;
        E element;
        int useCount;
        long timeStamp;

        @Override
        public int compareTo(@NotNull LFUCache0.Entry<K, E> o) {
            int result;
            return (result = Integer.compare(this.useCount, o.useCount)) != 0
                    ? result :
                    Long.compare(this.timeStamp, o.timeStamp);
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", element=" + element +
                    ", useCount=" + useCount +
                    ", timeStamp=" + timeStamp +
                    '}';
        }
    }
}
