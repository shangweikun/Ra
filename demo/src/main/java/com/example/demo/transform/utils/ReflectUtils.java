package com.example.demo.transform.utils;

import com.example.demo.transform.ConstructParam;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class ReflectUtils {
	private static final Comparator<Field> comparator
			= Comparator.comparing(field -> field.getAnnotation(ConstructParam.class).order());

	public static Class<?>[] getParameterTypes(Class<?> targetClass) {
		return Arrays.stream(targetClass.getFields())
				.filter(ReflectUtils::hasConstructParamAnnotation)
				.sorted(comparator)
				.map(ReflectUtils::getFiledClass)
				.toArray(Class<?>[]::new);
	}

	public static Class<?> getFiledClass(Field field) {
		return field.getAnnotation(ConstructParam.class).clazz();
	}

	public static boolean hasConstructParamAnnotation(Field field) {
		return field.getAnnotation(ConstructParam.class) != null;
	}
}
