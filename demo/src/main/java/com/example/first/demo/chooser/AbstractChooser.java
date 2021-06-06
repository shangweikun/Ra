package com.example.first.demo.chooser;

import lombok.Data;

@Data
public abstract class AbstractChooser<T> {

	protected T isTrueChoose;
	protected T isFalseChoose;

	public T choose(boolean result) {
		return result ? isTrueChoose : isFalseChoose;
	}
}
