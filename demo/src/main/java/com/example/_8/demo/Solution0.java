package com.example._8.demo;

import java.util.*;

public class Solution0 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        List<String> dict = new ArrayList<>(wordList);

        queue.add(beginWord);
        int layer = 1;
        while (!queue.isEmpty()) {
            layer++;
            int size = queue.size();
            while (size-- > 0) {
                String from = queue.poll();
                assert from != null;
                List<String> records = new ArrayList<>();
                for (String to : dict) {
                    if (canArrive(from, to)) {
                        if (to.equals(endWord)) {
                            return layer;
                        }
                        queue.add(to);
                    } else {
                        records.add(to);
                    }
                }
                dict = records;
            }
        }
        return 0;
    }

    private boolean canArrive(String from, String to) {
        int n = from.length();
        char[] cFrom = from.toCharArray();
        char[] cTo = to.toCharArray();
        int count = 0;
        for (int i = 0; i < n && count <= 1; i++) {
            if (cFrom[i] == cTo[i]) {
                continue;
            }
            count++;
        }
        return count == 1;
    }
}
