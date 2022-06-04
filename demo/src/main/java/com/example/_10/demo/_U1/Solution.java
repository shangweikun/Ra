package com.example._10.demo._U1;

/**
 * 优化1：将回溯方法中的 left right ext 三个位置变量转化为单个 flag 位置标识
 * <p>
 * 0-9 表示 | 竖向
 * <p>
 * 10-19 表示 \ 斜对接线
 * <p>
 * 20-29 表示 / 主对角线
 */
public class Solution {

    private final int[] changed = {1, 2, 4, 8, 16, 32, 64, 128, 256};

    private int result = 0;

    public int totalNQueens(int n) {
        findNQueues(0, 0, n, (1 << n) - 1);
        return result;
    }

    public int _8Queue() {
        findNQueues(0, 0, 8, 255);
        return result;
    }

    private void findNQueues(int deep,
                             int flag,
                             int n,
                             int calculate) {
        if (deep == n) {
            result++;
            return;
        }

        int left = flag >> 20;
        int right = (flag & 1047552) >> 10;
        int ext = flag & 1023;
        int tmp = (left | right | ext) & calculate;

        if (tmp == calculate) {
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((tmp & changed[i]) == 0) {
                tmp |= changed[i];
                findNQueues(deep + 1,
                        ((left | changed[i]) << 1) << 20
                                | ((((right | changed[i]) >> 1) << 10) & 1047552)
                                | ((ext | changed[i]) & 1023),
                        n,
                        calculate);
                tmp ^= changed[i];
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().totalNQueens(6));
        System.out.println(new Solution()._8Queue());
        System.out.println(Math.pow(2, 20) - 1 - 1023);
    }
}
