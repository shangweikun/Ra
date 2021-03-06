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
        System.out.println("???" + brackets.getLine() + "??????" +
                "???" + brackets.getOffset() + "????????????" +
                brackets.getBrackets() + "??????????????????"
        );
    }

    private static void printfMatchError(Brackets brackets1,
                                         Brackets brackets2) {
        System.out.println("???" + brackets1.getLine() + "??????" +
                "???" + brackets1.getOffset() + "????????????" +
                brackets1.getBrackets() + "?????????" +
                "???" + brackets2.getLine() + "??????" +
                "???" + brackets2.getOffset() + "????????????" +
                brackets2.getBrackets() + "???????????????"
        );
    }
}
