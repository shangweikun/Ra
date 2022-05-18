package com.example._10.demo._U2;

public class Solution {

    private int result = 0;

    public int totalNQueens(int n) {
        findNQueues(0, n, 0, (1 << n) - 1);
        return result;
    }

    public int _8Queue() {
        findNQueues(0, 8, 0, 255);
        return result;
    }

    private void findNQueues(int deep, int n, int flag, int calculate) {
        if (deep == n) {
            result++;
            return;
        }

        int tmp = (~((flag >> 20) |
                ((flag & 1047552) >> 10) |
                (flag & 1023)))
                & calculate;

        if (tmp == 0) {
            return;
        }

        for (int pos = tmp & (-tmp);
             tmp != 0;
             tmp = tmp & (tmp - 1),
                     pos = tmp & (-tmp)) {

            findNQueues(deep + 1,
                    n,
                    (((flag >> 20) | pos) << 1) << 20
                            | ((((((flag & 1047552) >> 10) | pos) >> 1) << 10) & 1047552)
                            | (((flag & 1023) | pos) & 1023),

                    calculate);
        }

    }

    public static void main(String[] args) {
        System.out.println(new Solution().totalNQueens(6));
        System.out.println(new Solution()._8Queue());
        System.out.println(Math.pow(2, 20) - 1 - 1023);
        System.out.println(~2 & -(~2));
    }
}
