package com.example._14.demo;

public class App {

	public static void main(String[] args) {
		System.out.println("Hello World");

		Object lock = new Object();

		Thread thread1 = new Thread(() -> {
			synchronized (lock) {
				System.out.println("thread1");
				while (true) {
					try {
						Thread.sleep(10000L);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});

		Thread thread2 = new Thread(() -> {
			synchronized (lock) {
				System.out.println("thread2");
				while (true) ;
			}
		});

		thread1.start();
		thread2.start();

	}

}
