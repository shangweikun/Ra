package com.example.first.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

	private String id;
	private String name;
	private String className;
}
