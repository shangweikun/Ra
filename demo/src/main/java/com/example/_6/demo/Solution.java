package com.example._6.demo;

import java.util.*;

public class Solution {

    private static final Map<String, String[]> map = new HashMap<>();

    static {
        String[] a = {"y", "z"};
        map.put("a", a);
        String[] b = {"x", "y", "z"};
        map.put("b", b);
        String[] c = {"y"};
        map.put("c", c);
    }

    static List<String> member = new ArrayList<>();

    public void main(String args) {
        map.entrySet().stream().sorted(Comparator.comparingInt(i -> i.getValue().length)).forEach(i -> {
            i.setValue(Arrays.stream(i.getValue()).filter(it -> !member.contains(it)).toList().toArray(new String[1]));
            member.add(i.getValue()[0]);
        });
        System.out.println(map);
    }

//        map.entrySet().stream().sorted(Comparator.comparingInt(i -> i.getValue().length)).reduce(new HashMap<String, String>(), (n, e) -> {n.put(e.getKey(), Arrays.stream(e.getValue()).dropWhile(n::containsValue).findFirst().orElseThrow());return n;}, (m1, m2) -> m2).forEach((k, v) -> System.out.println(k + "-" + v));

    public static void main(String[] args) {
        map.entrySet().stream()
                .sorted(Comparator.comparingInt(i -> i.getValue().length))
                .reduce(new HashMap<String, String>(),
                        (n, e) -> {
                            n.put(e.getKey(),
                                    Arrays.stream(e.getValue())
                                            .dropWhile(n::containsValue)
                                            .findFirst()
                                            .orElseThrow());
                            return n;
                        },
                        (m1, m2) -> m2).forEach((k, v) -> System.out.println(k + "-" + v));
    }
}
