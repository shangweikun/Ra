package com.example._8.demo;

import java.util.*;
import java.util.stream.Collectors;

public class Solution1 {

    public String ladderLength(String beginWord,
                               String endWord,
                               List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return "";
        }

        Map<String, List<String>> result = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        List<String> dict = new ArrayList<>(wordList);

        queue.add(beginWord);
        List<String> beginList = new ArrayList<>();
        beginList.add(beginWord);
        result.put(beginWord, beginList);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String from = queue.poll();
                assert from != null;
                List<String> records = new ArrayList<>();
                for (String to : dict) {
                    if (canArrive(from, to)) {
                        if (to.equals(endWord)) {
                            return result.get(from).get(0) + "->" + to;
                        }
                        queue.add(to);
                        result.put(to, result.get(from)
                                .stream()
                                .map(i -> i + "->" + to)
                                .collect(Collectors.toList()));
                    } else {
                        records.add(to);
                    }
                }
                dict = records;
            }
        }
        return "";
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
