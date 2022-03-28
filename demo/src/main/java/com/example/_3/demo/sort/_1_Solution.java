package com.example._3.demo.sort;

public class _1_Solution {
	public boolean containsDuplicate(int[] nums) {

		nums = sortIntegers(nums);
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				return true;
			}
		}
		return false;

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
