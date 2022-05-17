package com.example._10.demo;

public class Solution {

	private final int[] changed = {1, 2, 4, 8, 16, 32, 64, 128, 256};

	private int result = 0;

	public int totalNQueens(int n) {
		findNQueues(0, 0, 0, 0, n, (int) (Math.pow(2, n)) - 1);
		return result;
	}

	private void findNQueues(int deep,
	                         int left, int right, int ext,
	                         int n, int calculate) {
		if (deep == n) {
			result++;
			return;
		}

		int tmp = (left | right | ext) & calculate;
		if (tmp == calculate) {
			return;
		}

		for (int i = 0; i < n; i++) {
			if ((tmp & changed[i]) == 0) {
				tmp |= changed[i];
				findNQueues(deep + 1,
						(left | changed[i]) << 1, (right | changed[i]) >> 1,
						ext | changed[i], n, calculate);
				tmp ^= changed[i];
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().totalNQueens(8));
	}
}
