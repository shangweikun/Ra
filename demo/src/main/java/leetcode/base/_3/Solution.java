package leetcode.base._3;

public class Solution {

    static class Solution0 {

        public int maxArea(int[] height) {

            int result = Integer.MIN_VALUE;

            for (int i = 0; i < height.length - 1; i++) {

                int tmp = result / i + 1;

                for (int j = 0; j <= height.length - 1 - i; j++) {

                    if (height[j] >= tmp && height[j + i] >= tmp) {
                        int factor = Math.min(height[j], height[j + i]);
                        result = factor * i;
                        tmp = factor + 1;
                    }
                }
            }

            return result;
        }
    }

    static class Solution0End {

        public int maxArea(int[] height) {

            int i = 0;
            int j = height.length - 1;

            int result = 0;

            while (i < j) {

                result = Math.max(result,
                        height[i] < height[j] ?
                                (j - i) * height[i++] : (j - i) * height[j--]);

            }

            return result;
        }
    }


}
