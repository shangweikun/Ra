package com.example._7.demo;

public interface Cache<K, E> {

    E get(K key);

    void put(K key, E element);
}
