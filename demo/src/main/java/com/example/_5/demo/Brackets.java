package com.example._5.demo;

public class Brackets {
    private final char brackets;
    private final int line;
    private final int offset;

    public Brackets(char brackets, int line, int offset) {
        this.brackets = brackets;
        this.line = line;
        this.offset = offset;
    }

    public char getBrackets() {
        return brackets;
    }

    public int getLine() {
        return line;
    }

    public int getOffset() {
        return offset;
    }
}
