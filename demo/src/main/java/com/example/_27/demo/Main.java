package com.example._27.demo;

import java.util.Stack;
public class Main {

    private static int x = 0;
    private static volatile int y = 0;


    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            x = 1;
            y = 1;
        });

        thread.start();
        int r2 = y;
        int r1 = x;

        System.out.println(r1 + "," + r2);
    }

    static class Main0 {

        private static class ListNode {
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

        private static class Solution {

            public ListNode reverseList(ListNode head) {

                if (head == null) {
                    return head;
                }

                ListNode realHead = new ListNode();
                realHead.next = head;

                reversListMain(head, realHead);

                head.next = null;

                return realHead.next;
            }

            private void reversListMain(ListNode current, ListNode head) {

                if (current.next == null) {
                    head.next = current;
                } else {
                    ListNode tmp = current.next;
                    reversListMain(tmp, head);
                    tmp.next = current;
                }
            }
        }

        private static class Solution0 {

            public ListNode reverseList(ListNode head) {

                if (head == null) {
                    return null;
                }
                Stack<ListNode> stack = new Stack<>();
                stack.add(head);

                ListNode tmp = head.next;

                while (tmp!= null) {
                    stack.add(tmp);
                    tmp = tmp.next;
                }

                ListNode result = stack.pop();
                tmp = result;
                while (!stack.isEmpty()){
                    tmp.next = stack.pop();
                    tmp = tmp.next;
                }

                head.next = null;

                return result;
            }
        }

        public static void main(String[] args) {
            ListNode node1 = new ListNode(1);
            ListNode node2 = new ListNode(2);
            ListNode node3 = new ListNode(3);
            ListNode node4 = new ListNode(4);
            ListNode node5 = new ListNode(5);

            node1.next = node2;
            node2.next = node3;
            node3.next = node4;
            node4.next = node5;

            Solution solution = new Solution();
            ListNode result = solution.reverseList(node1);

        }
    }
}


