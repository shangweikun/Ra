package com.example._2.demo.queue;

import cn.hutool.core.collection.ListUtil;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class SyncKVHashBlockingQueue<K, E extends RedirectKey<K>>
		extends AbstractQueue<E> {

	private final int pool_size;
	private final ConcurrentHashMap<String, K> THREAD_LOCK = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<K, Entity<K, E>> QUEUE_CACHE = new ConcurrentHashMap<>();
	private final BlockingQueue<E> queue;

	public SyncKVHashBlockingQueue(int pool_size, BlockingQueue<E> queue) {
		this.pool_size = pool_size;
		this.queue = queue;
	}

	@Override
	public Iterator<E> iterator() {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

	@AllArgsConstructor
	private static class Entity<K, E> {
		final Object LOCK = new Object();
		K key;
		LinkedList<E> list;
	}

	@Override
	public E poll() {
		String threadName = Thread.currentThread().getName();
		K key;
		if ((key = THREAD_LOCK.get(threadName)) != null) {
			Entity<K, E> entity = QUEUE_CACHE.get(key);
			nullPoint:
			if (entity.list.getFirst() == null) {
				synchronized (entity.LOCK) {
					if (entity.list.getFirst() == null) {
						THREAD_LOCK.remove(threadName);
						break nullPoint;
					}
				}
				return entity.list.pollFirst();
			}
		}

		for (; ; ) {
			E e = queue.poll();
			if (e == null) {
				continue;
			}

			while (true) {
				Entity<K, E> head = QUEUE_CACHE.get(e.key);
				if (head == null) {
					head = new Entity<>(e.key, ListUtil.toLinkedList(e));
					for (; ; ) {
						synchronized (this) {
							Entity<K, E> checkKey = QUEUE_CACHE.get(e.key);
							if (checkKey == null && THREAD_LOCK.size() < pool_size) {
								THREAD_LOCK.put(threadName, head.key);
								QUEUE_CACHE.put(head.key, head);
								return head.list.pollFirst();
							}
						}
					}
				} else if (!THREAD_LOCK.containsValue(head.key)) {
					synchronized (head.LOCK) {
						if (!THREAD_LOCK.containsValue(head.key)) {
							if (THREAD_LOCK.size() < pool_size) {
								THREAD_LOCK.put(threadName, head.key);
								head.list.addLast(e);
								return head.list.pollFirst();
							}
						}
					}
				} else {
					synchronized (head.LOCK) {
						if (THREAD_LOCK.containsValue(head.key)) {
							head.list.add(e);
						}
					}
				}
			}
		}
	}

	@Override
	public E peek() {
		return null;
	}

	@Override
	public boolean offer(@NotNull E e) {
		Entity<K, E> entity;
		while ((entity = QUEUE_CACHE.get(e.key)) != null) {
			synchronized (entity.LOCK) {
				if (THREAD_LOCK.containsValue(e.key)) {
					entity.list.addLast(e);
				}
			}
		}
		return queue.add(e);
	}

}
