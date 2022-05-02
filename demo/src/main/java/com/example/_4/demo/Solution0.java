package com.example._4.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution0 {

    public final List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        int[] queue = new int[n];
        Arrays.fill(queue, -1);
        findSolution(queue, 0,
                0, 0, 0,
                (1 << n) - 1);
        return result;
    }

    private void findSolution(int[] strQ, int deep,
                              int horizontal, int diagonal1, int diagonal2,
                              int total) {

        if (deep >= strQ.length) {
            this.result.add(transformQueueString(strQ));
            return;
        }
        int availablePosition = (~(horizontal | diagonal1 | diagonal2)) & total;
        if (availablePosition == 0) {
            return;
        }

        for (int position = availablePosition & (-availablePosition);
             availablePosition != 0;
             availablePosition = availablePosition & (availablePosition - 1),
                     position = availablePosition & (-availablePosition)) {

            strQ[deep] = Integer.bitCount(position - 1);
            findSolution(strQ, deep + 1,
                    horizontal | position,
                    (diagonal1 | position) >> 1,
                    (diagonal2 | position) << 1,
                    total);
            strQ[deep] = -1;
        }
    }

    private List<String> transformQueueString(int[] strQ) {
        List<String> result = new ArrayList<>();
        for (int k : strQ) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < strQ.length; j++) {
                sb.append(j == k ? "Q" : ".");
            }
            result.add(sb.toString());
        }
        return result;
    }

}
