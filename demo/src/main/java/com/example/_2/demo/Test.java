package com.example._2.demo;

import scala.collection.Traversable;
import scala.collection.immutable.HashSet;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

	public static void main(String[] args) {
		BlockingDeque<String> deque = new LinkedBlockingDeque<>();
		BlockingQueue<String> queue = new LinkedBlockingQueue<>();

		test();
	}

	public static void test(){

		Traversable<String> test = new HashSet<>();
		System.out.println(Traversable.class.hashCode());
	}
}
