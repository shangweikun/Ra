package com.example._2.demo.queue;

import java.util.LinkedList;
import java.util.concurrent.Callable;

public abstract class SyncKVCallable<K, E> implements Callable<E>, Runnable {

	public final Object LOCK = new Object();
	public K key;
	public LinkedList<E> list;
	public volatile String threadName;

	public E syncCall() throws Exception {
		try {
			return call();
		} finally {

		}
	}
}
