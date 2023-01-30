package leetcode.base._4;


public class Solution {

    public void nextPermutation(int[] nums) {

        int i = nums.length - 1;

        while (i > 0 && nums[i] <= nums[i - 1]) {
            i--;
        }

        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int tail = i - 1;
        i = nums.length - 1;

        while (i > 0 && nums[tail] >= nums[i]) {
            i--;
        }

        int tmp = nums[tail];
        nums[tail] = nums[i];
        nums[i] = tmp;

        reverse(nums, tail + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int begin, int end) {
        int tmp;
        while (begin < end) {
            tmp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = tmp;
            begin++;
            end--;
        }
    }
}
