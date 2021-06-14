package com.example._2.demo.queue;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LinkedWrapper<E> {
	E value;
	LinkedWrapper<E> next;
}
