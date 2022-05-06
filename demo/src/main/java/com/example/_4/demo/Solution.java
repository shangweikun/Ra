package com.example._4.demo;

import java.util.*;

public class Solution {

    public final List<int[]> result = new ArrayList<>();

    public final Set<Integer> horizontal = new HashSet<>();

    public final Set<Integer> diagonal1 = new HashSet<>();

    public final Set<Integer> diagonal2 = new HashSet<>();

    public List<int[][]> solveNQueens(int n) {
        int[] solution = new int[n];
        Arrays.fill(solution, -1);
        findSolution(solution, 0, n);
        return transformerToRequire(result, n);
    }

    private List<int[][]> transformerToRequire(List<int[]> todo, int n) {
        List<int[][]> result = new ArrayList<>();
        for (int[] tmp : todo) {
            int[][] one = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(one[i], 0);
                one[i][tmp[i]] = 1;
            }
            result.add(one);
        }
        return result;
    }

    private void findSolution(int[] result, int deep, int n) {

        if (deep >= n) {
            this.result.add(Arrays.copyOf(result, n));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isHasQueue(i, deep)) {
                continue;
            }
            this.horizontal.add(i);
            this.diagonal1.add(deep - i);
            this.diagonal2.add(deep + i);
            result[deep] = i;
            findSolution(result, deep + 1, n);
            result[deep] = -1;
            this.horizontal.remove(i);
            this.diagonal1.remove(deep - i);
            this.diagonal2.remove(deep + i);
        }

    }

    private boolean isHasQueue(int i, int deep) {
        return this.horizontal.contains(i)
                || this.diagonal1.contains(deep - i)
                || this.diagonal2.contains(deep + i);
    }
}