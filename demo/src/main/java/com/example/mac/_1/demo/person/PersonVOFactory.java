package com.example.mac._1.demo.person;

public class PersonVOFactory {

	public static PersonVO newInstance(String key) {
		return new PersonVO(key);
	}
}
