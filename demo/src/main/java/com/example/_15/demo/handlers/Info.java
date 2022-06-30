package com.example._15.demo.handlers;

import lombok.Data;

@Data
public class Info {

	private long closedConnect;

	private long startConnect;

	private long allConnect;

	@Override
	public String toString() {
		return "|" + startConnect + "|" + closedConnect + "|" + allConnect + "|";
	}
}
