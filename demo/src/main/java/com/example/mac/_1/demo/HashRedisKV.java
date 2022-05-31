package com.example.mac._1.demo;

public class HashRedisKV<T> {

	public final String prefix;
	public final String key;
	public final T value;

	/**
	 * 1、延迟field获取；
	 * 2、减少一次性获取redis hash结构的过长等待的可能；
	 * 3、是否可以通过注解或者其他的一些方式进行呢；
	 */

	public HashRedisKV(String prefix, String key, T value) {
		this.prefix = prefix;
		this.key = key;
		this.value = value;
	}

	public void hSet(String field, Object obj) {
		RedisUtil.hSet(prefix + key, field, obj);
	}

	public Object hGet(String field) {
		return RedisUtil.hGet(prefix + key, field);
	}
}
