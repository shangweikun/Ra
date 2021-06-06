package com.example.first.demo.check.worker;

import com.example.first.demo.exception.NotSupportMethodException;

@SuppressWarnings("rawtypes")
public class FinalCheckWorker<R> extends CheckWorker<R> {

	private final boolean result;

	public FinalCheckWorker(boolean result) {
		super(null, null);
		this.result = result;
	}

	@Override
	public CheckWorker choose(boolean result) {
		throw new NotSupportMethodException();
	}

	public boolean result() {
		return result;
	}
}
