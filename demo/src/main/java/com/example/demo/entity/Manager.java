package com.example.demo.entity;

import com.example.demo.transform.AbstractTransformer;
import com.example.demo.transform.ConstructParam;

import java.util.List;

public class Manager {

	@ConstructParam(clazz = int.class)
	public int Id;
	@ConstructParam(order = 1, clazz = String.class)
	public String Name;
	@ConstructParam(order = 2, clazz = String.class)
	public String ClassName;
	@ConstructParam(order = 3, clazz = int.class)
	public int age;
	@ConstructParam(order = 4, clazz = List.class)
	public List<Person> persons;

	public Manager(int id, String name, String className, int age, List<Person> persons) {
		Id = id;
		Name = name;
		ClassName = className;
		this.age = age;
		this.persons = persons;
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

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "ManagerVO{" +
				"Id=" + Id +
				", Name='" + Name + '\'' +
				", ClassName='" + ClassName + '\'' +
				", age=" + age +
				", persons=" + persons +
				'}';
	}

	public static Manager ListToManager(List<Object> input)
			throws Exception {
		return Manager.transformer.ListToTarget(input);
	}

	private static final ManagerTransformer transformer;

	static {
		try {
			transformer = new ManagerTransformer(Manager.class);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	private static class ManagerTransformer extends AbstractTransformer<Manager> {
		private ManagerTransformer(Class<?> clazz) throws NoSuchMethodException {
			super.init(clazz);
		}
	}
}
