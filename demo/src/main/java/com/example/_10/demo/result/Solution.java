package com.example._10.demo.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private final int[] changed = {1, 2, 4, 8, 16, 32, 64, 128, 256};

    private final List<int[]> result = new ArrayList<>();

    private int[] temp;

    public List<int[][]> totalNQueens(int n) {
        temp = new int[n];
        findNQueues(0, 0, n, (1 << n) - 1);
        return transFrom(result, n);
    }

    private List<int[][]> transFrom(List<int[]> result, int n) {

        List<int[][]> finalResult = new ArrayList<>();
        for (int[] tmp : result) {
            int[][] one = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(one[i], 0);
                one[i][tmp[i]] = 1;
            }
            finalResult.add(one);
        }
        return finalResult;
    }

    private void findNQueues(int deep,
                             int flag,
                             int n,
                             int calculate) {

        if (deep == n) {
            result.add(Arrays.copyOf(temp, n));
        } else {
            int left = flag >> 20;
            int right = (flag & 1047552) >> 10;
            int ext = flag & 1023;
            int tmp = (left | right | ext) & calculate;

            if (tmp != calculate) {
                for (int i = 0; i < n; i++) {
                    if ((tmp & changed[i]) == 0) {
                        tmp |= changed[i];
                        temp[deep] = Integer.bitCount(changed[i] - 1);
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
        }
    }

}
