package com.example._15.demo.handlers.impl;

import com.example._15.demo.handlers.Info;
import com.example._15.demo.handlers.InfoHandle;

import java.util.List;

public class CloseInfo implements InfoHandle {
	@Override
	public String getColumnName() {
		return "closed";
	}

	@Override
	public void handle(Info info, List<String> input) {
		info.setClosedConnect(
				input.parallelStream()
						.filter(s -> s.contains("[F]"))
						.count()
		);
	}
}
