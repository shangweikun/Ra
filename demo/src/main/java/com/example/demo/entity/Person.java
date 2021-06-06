package com.example.demo.entity;

import com.example.demo.transform.AbstractTransformer;
import com.example.demo.transform.ConstructParam;

import java.util.List;

public class Person {

	@ConstructParam(clazz = int.class)
	public int Id;
	@ConstructParam(order = 1, clazz = String.class)
	public String Name;
	@ConstructParam(order = 2, clazz = String.class)
	public String ClassName;
	@ConstructParam(order = 3, clazz = int.class)
	public int age;

	public Person(int id, String name, String className, int age) {
		Id = id;
		Name = name;
		ClassName = className;
		this.age = age;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person{" +
				"Id=" + Id +
				", Name='" + Name + '\'' +
				", ClassName='" + ClassName + '\'' +
				", age=" + age +
				'}';
	}

	public static Person ListToPerson(List<Object> input) throws Exception {
		return Person.transformer.ListToTarget(input);
	}

	private static final PersonTransformer transformer;

	static {
		try {
			transformer = new PersonTransformer(Person.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private static class PersonTransformer extends AbstractTransformer<Person> {
		private PersonTransformer(Class<?> clazz) throws NoSuchMethodException {
			super.init(clazz);
		}
	}
}
