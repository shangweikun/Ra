package com.example._1.demo.check;

public abstract class AbstractChecker<T> implements Checker<T> {

	@SuppressWarnings("unchecked")
	protected boolean checkWithInstance(Object obj) {
		return check((T) obj);
	}

}
