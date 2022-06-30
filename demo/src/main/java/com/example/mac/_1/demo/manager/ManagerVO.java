package com.example.mac._1.demo.manager;

import com.example.mac._1.demo.AbstractHashRedisBean;

public class ManagerVO extends AbstractHashRedisBean {

	public ManagerVO(String key) {
		super("MANAGER", key);
	}

}
