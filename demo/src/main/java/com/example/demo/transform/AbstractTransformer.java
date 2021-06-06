package com.example.demo.transform;

import com.example.demo.transform.utils.ReflectUtils;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class AbstractTransformer<T> {

	private Constructor<?> constructor;

	public Constructor<?> getConstructor() {
		return constructor;
	}

	@SuppressWarnings("unchecked")
	public T ListToTarget(List<Object> input)
			throws Exception {
		return (T) getConstructor().newInstance(input.toArray());
	}

	public void init(Class<?> clazz)
			throws NoSuchMethodException {
		Class<?>[] parameterTypes = ReflectUtils.getParameterTypes(clazz);
		this.constructor = clazz.getConstructor(parameterTypes);
	}
}
