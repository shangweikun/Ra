package com.example._1.demo.collect;

public interface Collector<T, R> {
	R collectInfo(T t);
}
