package com.example._3.demo;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution {

    public Map<TreeNode,Integer> note = new HashMap<>();

    public Stack<TreeNode> stack = new Stack<>();

    public void pushLeftTree(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

    }

    public List<Integer> inorderTraversal(TreeNode root) {

        pushLeftTree(root);

        List<Integer> result = new LinkedList<>();

        while (!stack.isEmpty()) {

            TreeNode curt = stack.peek();
            result.add(curt.val);
            if (curt.right == null) {
                TreeNode node = stack.pop();
                while (!stack.isEmpty() && stack.peek().right == node) {
                    node = stack.pop();
                }
            } else {
                pushLeftTree(curt.right);
            }
        }

        int i =note.values().stream()
                .max(Integer::compareTo)
                .orElse(0);
        System.out.println(i);

        return result;

    }
}
