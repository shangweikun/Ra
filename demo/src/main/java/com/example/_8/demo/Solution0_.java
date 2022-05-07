package com.example._8.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution0_ {

    /**
     * bfs 采用标记丸形势
     *
     * @param beginWord ;
     * @param endWord   ;
     * @param wordList  ;
     * @return ;
     */
    public int ladderLength(String beginWord, String endWord,
                            List<String> wordList) {

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        queue.add("");
        List<String> dict = wordList;
        int layer = 1;

        while (!queue.isEmpty()) {
            String from = queue.poll();
            if ("".equals(from)) {
                if (queue.isEmpty()) {
                    break;
                }
                layer++;
                queue.add("");
            } else {
                List<String> records = new ArrayList<>();
                for (String to : dict) {
                    if (canArrive(from, to)) {
                        if (to.equals(endWord)) {
                            return layer + 1;
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
