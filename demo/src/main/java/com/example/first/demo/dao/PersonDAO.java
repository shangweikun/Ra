package com.example.first.demo.dao;

import com.example.first.demo.entity.Person;

public interface PersonDAO {
	Person selectById(String id);
}
