package com.example.mac._1.demo;

public abstract class AbstractHashRedisBean {

	public final HashRedisKV<Object> KV;

	public AbstractHashRedisBean(String prefix, String key) {
		this.KV = new HashRedisKV<>(prefix, key, new Object());
	}
}
