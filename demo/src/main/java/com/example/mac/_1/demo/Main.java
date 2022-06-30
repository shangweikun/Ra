package com.example.mac._1.demo;

import com.example.mac._1.demo.person.PersonVO;
import com.example.mac._1.demo.person.PersonVOFactory;

public class Main {

	public static void main(String[] args) {
		PersonVO VO = PersonVOFactory.newInstance("712345");
		System.out.println(VO);
	}
}
