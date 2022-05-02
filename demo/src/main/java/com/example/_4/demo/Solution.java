package com.example._4.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public final List<List<String>> result = new ArrayList<>();

    public final Set<Integer> horizontal = new HashSet<>();

    public final Set<Integer> diagonal1 = new HashSet<>();

    public final Set<Integer> diagonal2 = new HashSet<>();

    public int deepTotal;

    public List<List<String>> solveNQueens(int n) {
        this.deepTotal = n;
        ArrayList<String> strQ = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            this.horizontal.add(i);
            this.diagonal1.add(-i);
            this.diagonal2.add(i);
            strQ.add(0, getQueueStringByHorizontal(i, this.deepTotal));
            findSolution(strQ, 1);
            strQ.remove(0);
            this.horizontal.remove(i);
            this.diagonal1.remove(-i);
            this.diagonal2.remove(i);
        }
        return result;
    }

    private void findSolution(ArrayList<String> strQ, int deep) {

        if (deep >= this.deepTotal) {
            this.result.add(new ArrayList<>(strQ));
            return;
        }

        for (int i = 0; i < this.deepTotal; i++) {
            if (isHasQueue(i, deep)) {
                continue;
            }
            this.horizontal.add(i);
            this.diagonal1.add(deep - i);
            this.diagonal2.add(deep + i);
            strQ.add(deep, getQueueStringByHorizontal(i, deepTotal));
            findSolution(strQ, deep + 1);
            strQ.remove(deep);
            this.horizontal.remove(i);
            this.diagonal1.remove(deep - i);
            this.diagonal2.remove(deep + i);
        }

    }

    private String getQueueStringByHorizontal(int sit, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(i == sit ? "Q" : ".");
        }
        return sb.toString();
    }

    private boolean isHasQueue(int i, int deep) {
        return this.horizontal.contains(i)
                || this.diagonal1.contains(deep - i)
                || this.diagonal2.contains(deep + i);
    }
}