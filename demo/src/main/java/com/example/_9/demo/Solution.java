package com.example._9.demo;

public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int length = nums1.length + nums2.length;
        int mid = length / 2;
        int i = 0, j = 0, k = 0;
        boolean mark = false;

        if (nums1.length == 0) {
            return length % 2 == 0 ? ((double) nums2[mid - 1] + nums2[mid]) / 2 :
                    nums2[mid];
        }

        if (nums2.length == 0) {
            return length % 2 == 0 ? ((double) nums1[mid - 1] + nums1[mid]) / 2 :
                    nums1[mid];
        }

        for (; i < nums1.length && j < nums2.length && k < mid + 1; k++) {

            if (nums1[i] < nums2[j]) {
                i++;
                mark = true;
                continue;
            }

            if (nums1[i] >= nums2[j]) {
                j++;
                mark = false;
                continue;
            }

            if (k == mid - 1) {
                break;
            }

        }

        if (i == nums1.length) {
            if (length % 2 == 1) {
                if (k <= mid) {
                    return nums2[j + mid - k];
                } else {
                    return nums1[i - 1];
                }
            } else {
                if (k <= mid - 1) {
                    return ((double) nums2[j + mid - 1 - k]
                            + nums2[j + mid - k]) / 2;
                } else if (k < mid + 1) {
                    return ((double) nums1[i - 1] +
                            (j == 0 ? nums2[0] :
                                    getTmp(i - 1 - 1, k == mid ? j
                                            : j - 1, nums1, nums2))) / 2;
                }
            }
        }

        if (j == nums2.length) {
            if (length % 2 == 1) {
                if (k <= mid) {
                    return nums1[i + mid - k];
                } else {
                    return nums2[j - 1];
                }
            } else {
                if (k <= mid - 1) {
                    return ((double) nums1[i + mid - 1 - k]
                            + nums1[i + mid - k]) / 2;
                } else if (k < mid + 1) {
                    return ((double) nums2[j - 1] + (i == 0 ? nums1[0] :
                            getTmp(k == mid ? i :
                                    i - 1, j - 1 - 1, nums1, nums2))) / 2;
                }
            }
        }

        int result = mark ? nums1[i - 1] : nums2[j - 1];
        if (length % 2 == 1) {
            return result;
        } else {
            int tmp = getTmp(mark ? i - 1 - 1 : i - 1,
                    mark ? j - 1 : j - 1 - 1,
                    nums1, nums2);
            return ((double) tmp + result) / 2;
        }
    }

    private int getTmp(int i, int j, int[] nums1, int[] nums2) {
        if (i < 0) {
            return nums2[j];
        }
        if (j < 0) {
            return nums1[i];
        }

        return Math.max(nums1[i], nums2[j]);
    }

}
