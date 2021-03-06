package com.example._1.demo.check.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example._1.demo.check.AbstractChecker;
import com.example._1.demo.entity.Person;

import java.util.regex.Pattern;

public class PersonIdChecker extends AbstractChecker<Person> {

	private static final String ID_PATTERN = "P[0-9]+";

	@Override
	public boolean check(Person people) {
		return ObjectUtil.isNotEmpty(people)
				&& Pattern.matches(ID_PATTERN, people.getId());
	}
}
