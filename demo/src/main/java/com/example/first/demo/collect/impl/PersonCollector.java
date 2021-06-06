package com.example.first.demo.collect.impl;

import com.example.first.demo.collect.Collector;
import com.example.first.demo.dao.PersonDAO;
import com.example.first.demo.entity.Person;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonCollector implements Collector<String, Person> {

	private final PersonDAO dao;

	@Override
	public Person collectInfo(String id) {
//		return dao.selectById(id);
		return Person.builder()
				.id(id)
				.className("5年一班")
				.build();
	}

}
