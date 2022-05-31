package com.example.mac._1.demo.person;

import com.example.mac._1.demo.HashRedisKV;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class PersonVOInvocation implements InvocationHandler {

	private final HashRedisKV<PersonVO> KV;
	private final PersonVO target;

	public PersonVOInvocation(HashRedisKV<PersonVO> KV, PersonVO target) {
		this.KV = KV;
		this.target = target;
	}

	@Override
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
		if (method.getName().startsWith("get")) {
			Object result = method.invoke(target, objects);
			if (result == null) {
				return PersonHandler.BasicHandler.getValue(KV);
			}
			return result;
		} else {
			return method.invoke(target, objects);
		}
	}
}
