package com.example._1.demo.collect.impl;

import com.example._1.demo.collect.Collector;
import com.example._1.demo.dao.PersonDAO;
import com.example._1.demo.entity.Person;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonCollector implements Collector<String, Person> {

	private final PersonDAO dao;

	@Override
	public Person collectInfo(String id) {
		return dao.selectById(id);
	}

	public static void main(String[] args) {
		System.out.println(1);
	}

}
