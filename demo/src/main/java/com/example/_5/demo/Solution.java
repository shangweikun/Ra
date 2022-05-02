package com.example._5.demo;

import java.io.*;
import java.util.*;

public class Solution {

    private static final char curlyLeft = '{';
    private static final char curlyRight = '}';
    private static final char squareLeft = '[';
    private static final char squareRight = ']';
    private static final char parenthesesLeft = '(';
    private static final char parenthesesRight = ')';

    private static final Set<Character> notes = new HashSet<>();

    static {
        notes.add(curlyLeft);
        notes.add(squareLeft);
        notes.add(parenthesesLeft);
    }

    private static final Map<Character, Character> mapNotes = new HashMap<>();

    private static final Stack<Brackets> stack = new Stack<>();

    static {
        mapNotes.put(curlyRight, curlyLeft);
        mapNotes.put(squareRight, squareLeft);
        mapNotes.put(parenthesesRight, parenthesesLeft);
    }

    public static boolean checkJavaFile(File file) {

        int line = 0;
        int offset;
        try (BufferedReader d = new BufferedReader(new InputStreamReader(
                new FileInputStream(file)))
        ) {
            while (d.ready()) {
                line++;
                offset = 0;
                char[] lines = d.readLine().toCharArray();
                for (char tmp : lines) {
                    offset++;
                    if (notes.contains(tmp)) {
                        stack.add(new Brackets(tmp, line, offset));
                        continue;
                    }
                    if (mapNotes.containsKey(tmp)) {
                        Brackets brackets = new Brackets(tmp, line, offset);
                        if (stack.isEmpty()) {
                            printfMatchLack(brackets);
                            return false;
                        }
                        Brackets bracketsTmp;
                        if ((bracketsTmp = stack.pop()).getBrackets()
                                != mapNotes.get(tmp)) {
                            printfMatchError(bracketsTmp, brackets);
                            return false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!stack.isEmpty()) {
            printfMatchLack(stack.peek());
        }

        return stack.isEmpty();
    }

    private static void printfMatchLack(Brackets brackets) {
        System.out.println("第" + brackets.getLine() + "行，" +
                "第" + brackets.getOffset() + "个位置的" +
                brackets.getBrackets() + "括号缺少匹配"
        );
    }

    private static void printfMatchError(Brackets brackets1,
                                         Brackets brackets2) {
        System.out.println("第" + brackets1.getLine() + "行，" +
                "第" + brackets1.getOffset() + "个位置的" +
                brackets1.getBrackets() + "括号与" +
                "第" + brackets2.getLine() + "行，" +
                "第" + brackets2.getOffset() + "个位置的" +
                brackets2.getBrackets() + "括号不匹配"
        );
    }

    public static void main(String[] args) {
        System.out.println(checkJavaFile(
                new File("C:\\tmp\\Solution.java"))
        );
    }
}
