package com.example._3.demo.sort;

public class Solution {

	public static void main(String[] args) {
		Solution solution = new Solution();
		for (int i : solution.sortIntegers(new int[]{5,2,2})
		) {
			System.out.print(i + ",");
		}
	}

	public int[] sortIntegers(int[] A) {
		quickSort(A, 0, A.length - 1);
		return A;
	}

	private void quickSort(int[] a, int start, int end) {

		if (start >= end) {
			return;
		}

		int left = start, right = end;
		int pivot = a[(start + end) / 2];

		while (left <= right) {
			while (left <= right && a[left] < pivot) {
				left++;
			}
			while (left <= right && a[right] > pivot) {
				right--;
			}
			if (left <= right) {
				int tmp = a[right];
				a[right] = a[left];
				a[left] = tmp;

				right--;
				left++;
			}
		}

		quickSort(a, start, right);
		quickSort(a, left, end);
	}
}
