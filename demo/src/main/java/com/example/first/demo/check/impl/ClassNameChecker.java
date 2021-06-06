package com.example.first.demo.check.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.first.demo.check.AbstractChecker;
import com.example.first.demo.entity.Person;

public class ClassNameChecker extends AbstractChecker<Person> {

	private static final String CHECK_CLASS_NAME = "5年一班";

	@Override
	public boolean check(Person people) {
		return ObjectUtil.isNotEmpty(people)
				&& CHECK_CLASS_NAME.equals(people.getClassName());
	}
}
