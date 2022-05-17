package com.example._4.demo;

import java.util.Stack;

public class Solution1 {

	private static final byte[] changed = {-128, 64, 32, 16, 8, 4, 2, 1};
	private static final int[] changedInt = {128, 64, 32, 16, 8, 4, 2, 1};
	private static Stack<Byte> stack = new Stack<>();
	private static Stack<Integer> stackInt = new Stack<>();
	private static int i = 0;

	/**
	 * 目标：用 1 byte 去判断 8 个皇后位置
	 * 失败1：因为左移后和右移后的结果可以向下传递，但是竖向计算的结果不能传递
	 * 失败2：byte类型在java中不支持操作
	 *
	 * 失败1说明：
	 * <p>
	 * 例如 1 0 0 0 0 0 0 0
	 * <p>
	 * 左移 0 0 0 0 0 0 0 0
	 * <p>
	 * 右移 0 1 0 0 0 0 0 0
	 * <p>
	 * 或后 1 1 0 0 0 0 0 1 （ 假如选择 最后一位 ）
	 * <p>
	 * 如果用于下一次递归时：
	 * <p>
	 * 左移 1 0 0 0 0 0 1 0 （ 正确 0 0 0 0 0 0 1 0 多计算了第一行的数据 ）
	 * <p>
	 * 右移 0 1 1 0 0 0 0 0 （ 正确 0 0 1 0 0 0 0 0 ）
	 * <p>
	 * 对于对接线判断会出现错误
	 *
	 * @return 返回所有的结果
	 */
	public static void _8Queue() {
		callDFSByInt(0, 0, 0, 0);
	}

	private static void callDFS(int deep, byte left, byte right, byte ext) {

		if (deep == 8) {
			System.out.println(++i);
			return;
		}

		byte tmp = (byte) (left | right | ext);

		if (tmp == (Byte.MAX_VALUE | Byte.MIN_VALUE)) {
			return;
		}

		for (byte one : changed) {
			if (((byte) (one & tmp)) == 0) {
				tmp = (byte) (tmp | one);
				stack.add(one);
				callDFS(deep + 1,
						(byte) (((byte) (left | one)) << 1),
						(byte) (((byte) (right | one)) >> 1),
						(byte) (ext | one));
				stack.pop();
				tmp = (byte) (tmp ^ one);
			}
		}
	}

	private static void callDFSByInt(int deep, int left, int right, int ext) {

		if (deep == 8) {
			System.out.println(++i);
			return;
		}

		int tmp = left | right | ext;

		if (tmp == 255) {
			return;
		}

		for (int one : changedInt) {
			if ((one & tmp) == 0) {
				tmp |= one;
				stackInt.add(one);
				callDFSByInt(deep + 1,
						(left | one) << 1,
						(right | one) >> 1,
						ext | one);
				stackInt.pop();
				tmp ^= one;
			}
		}
	}

	/**
	 * 用长度为8的byte数组表示每个位置的变化值 - 固定不变的
	 */
	public static void main(String[] args) {
		/*
		 *  失败2说明：不支持byte类型的位移操作
		 */
		byte tmp = -128;
		System.out.println( (byte) (tmp >>> 1)); // -64 不支持byte类型的位移操作
		int tmpInt = -128;
		System.out.println( (byte) (tmpInt >>> 1)); // -64 实际为转换为int后的位移
		_8Queue();
	}
}
