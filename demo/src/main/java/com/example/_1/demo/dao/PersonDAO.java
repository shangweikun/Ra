package com.example._1.demo.dao;

import com.example._1.demo.entity.Person;

public interface PersonDAO {
	Person selectById(String id);
}
