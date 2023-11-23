package leetcode.base._2;

import java.util.*;

public class Solution {

    static class Solution0 {
        public int maxProfit(int[] prices) {

            if (prices.length == 0 || prices.length == 1) {
                return 0;
            }

            int profit = 0;
            int buy = prices[0];

            for (int i = 1; i < prices.length; i++) {

                if (prices[i] < buy) {
                    buy = prices[i];
                } else if ((prices[i] - buy) > profit) {
                    profit = prices[i] - buy;
                }
            }

            return profit;
        }
    }

    static class Solution2 {
        public String longestPalindrome(String s) {

            if (s.length() <= 1) {
                return s;
            }

            if (s.length() == 2) {
                return s.charAt(0) == s.charAt(1) ?
                        s : String.valueOf(s.charAt(0));
            }

            int left = 0;
            int right = 0;

            int maxLength = 1;

            int length = s.length();
            boolean[][] result = new boolean[length][length];

            for (int i = 0; i < length - 1; i++) {
                result[i][i] = true;
                result[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
                if (maxLength < 2 && result[i][i + 1]) {
                    left = i;
                    right = i + 1;
                    maxLength = 2;
                }
            }

            for (int L = 2; L < length; L++) {
                for (int i = 0; i < length; i++) {
                    int j = i + L;

                    if (j >= length) {
                        break;
                    }

                    result[i][j] = result[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                    if (result[i][j] && maxLength < ((j - i) + 1)) {
                        left = i;
                        right = j;
                        maxLength = j - i + 1;
                    }
                }
            }

            return s.substring(left, right + 1);

        }
    }

    static class Solution3 {

        Map<Character, char[]> dict = new HashMap<>();

        {
            dict.put('2', new char[]{'a', 'b', 'c'});
            dict.put('3', new char[]{'d', 'e', 'f'});
            dict.put('4', new char[]{'g', 'h', 'i'});
            dict.put('5', new char[]{'j', 'k', 'l'});
            dict.put('6', new char[]{'m', 'n', 'o'});
            dict.put('7', new char[]{'p', 'q', 'r', 's'});
            dict.put('8', new char[]{'t', 'u', 'v'});
            dict.put('9', new char[]{'w', 'x', 'y', 'z'});
        }

        Map<Character, String[]> dict0 = new HashMap<>();

        {
            dict0.put('2', new String[]{"a", "b", "c"});
            dict0.put('3', new String[]{"d", "e", "f"});
            dict0.put('4', new String[]{"g", "h", "i"});
            dict0.put('5', new String[]{"j", "k", "l"});
            dict0.put('6', new String[]{"m", "n", "o"});
            dict0.put('7', new String[]{"p", "q", "r", "s"});
            dict0.put('8', new String[]{"t", "u", "v"});
            dict0.put('9', new String[]{"w", "x", "y", "z"});
        }

        LinkedList<String> queue = new LinkedList<>();

        public List<String> letterCombinations0(String digits) {

            for (char c : digits.toCharArray()) {
                if (queue.isEmpty()) {
                    queue.addAll(List.of(dict0.get(c)));
                } else {
                    while (true) {
                        String tmp = queue.poll();
                        for (String s : dict0.get(c)) {
                            queue.add(tmp + s);
                        }
                        if (queue.peek() == null ||
                                tmp.length() != queue.peek().length()) {
                            break;
                        }
                    }
                }
            }
            return queue;
        }

        public List<String> letterCombinations(String digits) {

            if (digits.length() == 0) {
                return new ArrayList<>();
            }

            List<String> result = new ArrayList<>();

            for (char c : digits.toCharArray()) {
                char[] tmp = dict.get(c);
                if (result.isEmpty()) {
                    for (char c1 : tmp) {
                        result.add(String.valueOf(c1));
                    }
                } else {
                    List<String> resultTmp = new ArrayList<>();

                    for (String str : result) {
                        for (char c1 : tmp) {
                            resultTmp.add(str + c1);
                        }
                    }
                    result = resultTmp;
                }
            }

            return result;

        }
    }

    static class Solution4 {

        public static class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        public ListNode removeNthFromEnd(ListNode head, int n) {

            if (head.next == null && n == 1) {
                return null;
            }

            ListNode begin = new ListNode(-1, head);
            ListNode slow = begin;
            ListNode faster = begin;

            for (int i = 0; i < n; i++) {
                faster = faster.next;
            }

            while (true) {

                if (faster.next == null) {
                    slow.next = slow.next.next;
                    break;
                }
                slow = slow.next;
                faster = faster.next;
            }

            return begin.next;
        }
    }

    static class Solution5 {

        private Map<Integer, List<String>> note = new HashMap<>();

        public List<String> generateParenthesis(int n) {
            return doGenerate(n);
        }

        private List<String> doGenerate(int n) {

            if (n == 1) {
                return List.of("()");
            } else if (note.containsKey(n)) {
                return note.get(n);
            } else {

                List<String> tmp = new ArrayList<>(doGenerate(n - 1)
                        .stream()
                        .map(str -> List.of("(" + str + ")", "()" + str, str + "()"))
                        .flatMap(List::stream)
                        .distinct()
                        .toList());

                for (int i = 2; i < n; i++) {
                    List<String> left = doGenerate(i);
                    List<String> right = doGenerate(n - i);
                    tmp.addAll(mergeList(left, right));
                }

                List<String> result = tmp
                        .stream()
                        .distinct()
                        .toList();

                note.put(n, result);
                return result;
            }

        }

        private List<String> mergeList(List<String> left, List<String> right) {

            return left.stream()
                    .map(lStr -> right.stream().map(rStr -> lStr + rStr).toList())
                    .flatMap(List::stream)
                    .distinct()
                    .toList();


        }

        private List<String> result = new ArrayList<>();

        private void doGenerate0(String s, int n, int l, int r) {

            if (n == l && n == r) {
                result.add(s);
            }

            if (l > n || r > n || r > l) return;

            doGenerate0(s + "(", n, l + 1, r);
            doGenerate0(s + ")", n, l, r + 1);

        }
    }
}
