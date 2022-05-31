package com.example.mac._1.demo;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class DefaultHandler<P, V> {

	private final Function<String, P> function;
	private final Function<V, P> getter;
	private final BiConsumer<V, P> setter;
	private final String field;


	public DefaultHandler(Function<String, P> function,
	                      Function<V, P> getter,
	                      BiConsumer<V, P> setter,
	                      String field) {
		this.function = function;
		this.getter = getter;
		this.setter = setter;
		this.field = field;
	}

	public void setValue(HashRedisKV<V> KV) {
		P result;
		setter.accept(KV.value, result = function.apply(KV.key));
		KV.hSet(field, result);
	}

	public P getValue(HashRedisKV<V> KV) {

		synchronized (KV.value) {
			@SuppressWarnings("unchecked")
			P param = (P) KV.hGet(field);

			if (param == null) {
				setValue(KV);
			}
			setter.accept(KV.value, param);
		}
		return getter.apply(KV.value);
	}
}
