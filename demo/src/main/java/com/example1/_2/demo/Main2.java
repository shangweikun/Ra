package com.example1._2.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {


    public static class TreeNode {
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

    static class Solution {

        public record NoteStruct(int length, List<TreeNode> treeNodeWrappers) {

            public List<TreeNode> result(int[] arrays) {
                return treeNodeWrappers.stream()
                        .map(t -> this.toRealTreeNode(t, arrays))
                        .collect(Collectors.toList());
            }

            private TreeNode toRealTreeNode(TreeNode treeNodeWrapper,
                                            int[] arrays) {

                if (treeNodeWrapper == null || arrays.length == 0) {
                    return null;
                }

                TreeNode cloneTreeNode = new TreeNode();
                cloneTreeNode.left = toRealTreeNode(cloneTreeNode.left, arrays);
                cloneTreeNode.right = toRealTreeNode(cloneTreeNode.right, arrays);
                cloneTreeNode.val = arrays[treeNodeWrapper.val - 1];

                return cloneTreeNode;
            }
        }

        public static void main(String[] args) {
            List<TreeNode> treeNodes = new Solution().generateTrees(1);
            treeNodes.forEach(System.out::println);
        }

        public List<TreeNode> generateTrees(int n) {

            List<NoteStruct> noteStructs = new ArrayList<>(n);

            for (int i = 1; i <= n; i++) {
                noteStructs.add(i - 1, generateNotes(i, noteStructs));
            }

            List<TreeNode> result = new ArrayList<>();

            for (int i = 1; i <= n; i++) {
                TreeNode treeNode = new TreeNode();
                treeNode.val = i;
                int[] arraysLeft = newArraysLeft(i);
                int[] arraysRight = newArraysRight(i, n);
                NoteStruct noteStructLeft = i - 1 <= 0 ? null :
                        noteStructs.get(i - 1 - 1);
                NoteStruct noteStructRight = n - i == 0 ? null :
                        noteStructs.get(n - i - 1);


                List<TreeNode> treeNodeWrapperLefts = noteStructLeft == null ?
                        new ArrayList<>() :
                        noteStructLeft.result(arraysLeft);
                List<TreeNode> treeNodeWrapperRights = noteStructRight == null ?
                        new ArrayList<>() :
                        noteStructRight.result(arraysRight);

                List<TreeNode> maxLoopTreeNodes = treeNodeWrapperLefts.size() < treeNodeWrapperRights.size() ?
                        treeNodeWrapperRights : treeNodeWrapperLefts;
                List<TreeNode> minLoopTreeNodes = treeNodeWrapperLefts.size() >= treeNodeWrapperRights.size() ?
                        treeNodeWrapperRights : treeNodeWrapperLefts;

                boolean lf = treeNodeWrapperLefts.size() >= treeNodeWrapperRights.size();

                for (TreeNode treeNodeWrapper1 : maxLoopTreeNodes) {

                    if (lf) {
                        treeNode.left = treeNodeWrapper1;
                    } else {
                        treeNode.right = treeNodeWrapper1;
                    }
                    for (TreeNode treeNodeWrapper2 : minLoopTreeNodes) {
                        if (lf) {
                            treeNode.right = treeNodeWrapper2;
                        } else {
                            treeNode.left = treeNodeWrapper2;
                        }
                        result.add(treeNode);
                        treeNode = new TreeNode();
                        treeNode.val = i;
                    }
                }
            }
            return result;
        }

        private int[] newArraysRight(int i, int n) {
            List<Integer> result = new ArrayList<>();
            for (int j = i + 1; j <= n; j++) {
                result.add(j);
            }
            return result.stream().mapToInt(t -> t).toArray();
        }

        private int[] newArraysLeft(int i) {
            List<Integer> result = new ArrayList<>();
            for (int j = i + 1; j < i; j++) {
                result.add(j);
            }
            return result.stream().mapToInt(t -> t).toArray();
        }

        private NoteStruct generateNotes(int i, List<NoteStruct> noteStructs) {

            List<TreeNode> treeNodes = new ArrayList<>();
            if (i == 1) {
                TreeNode treeNode = new TreeNode();
                treeNode.left = null;
                treeNode.right = null;
                treeNode.val = 1;
                treeNodes.add(treeNode);
            } else {
                NoteStruct noteStruct = noteStructs.get(i - 2);
                List<TreeNode> treeNodeWrappers = noteStruct.treeNodeWrappers;
                for (TreeNode treeNodeWrapper : treeNodeWrappers) {
                    TreeNode treeNode = new TreeNode();
                    treeNode.val = i;
                    treeNode.left = treeNodeWrapper;
                    treeNodes.add(treeNode);
                    treeNode = new TreeNode();
                    treeNode.val = i;
                    treeNode.right = treeNodeWrapper;
                    treeNodes.add(treeNode);
                }
            }
            return new NoteStruct(i, treeNodes);
        }
    }

}
