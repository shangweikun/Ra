package com.example._31.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    static class PatternTarget extends PatternKey {
        final String target;

        PatternTarget(String target) {
            this.target = target;
        }

        @Override
        public boolean equals(Object o) {

            if (o instanceof PatternTarget patternTarget && patternTarget.target.matches(pattern)) {
                return true;
            }
            return (o instanceof PatternKey patternKey) && target.matches(patternKey.pattern);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    static class PatternKey {

        void setPattern(String pattern) {
            this.pattern = pattern;
        }

        String pattern = "123$";

        @Override
        public boolean equals(Object o) {
            if (o instanceof PatternTarget patternTarget && patternTarget.target.matches(pattern)) {
                patternTarget.setPattern(pattern);
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public static void main(String[] args) {

        Map<PatternKey, String> map = new HashMap<>();

        PatternTarget target = new PatternTarget("123");
        map.put(new PatternKey(), "1");
        System.out.println(map.get(target));
        System.out.println(target.pattern);
        System.out.println(target.hashCode());
    }


    class Switcher {
        Map<String, String> map = new HashMap<>();

        String replace(String input) {
            Map.Entry<String, String> entry = map.entrySet().parallelStream()
                    .filter(e -> Pattern.compile(e.getKey()).matcher(input).matches())
                    .findAny()
                    .orElse(null);

            if (entry != null) {
                return input.replace(entry.getKey(), entry.getValue());
            }

            return input;

        }

    }
}
