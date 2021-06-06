package com.example.first.demo.collect;

public interface Collector<T, R> {
	R collectInfo(T t);
}
