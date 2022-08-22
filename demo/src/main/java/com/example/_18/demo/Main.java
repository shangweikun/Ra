package com.example._18.demo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String demo = "HELLO WORD";
        List<String> list = new ArrayList<>();


        String s = "Hello";
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        String s0 = sb.toString();
        String s1 = sb.toString();
        String s2 = "Hello";

        String s3 = "Hel";
        String s4 = "lo";

        String s5 = "Hel" + "lo";
        String s6 = "Hel" + s4;
        String s7 = s3 + "lo";


        System.out.println(s == s0);
        System.out.println(s0 == s1);
        System.out.println(s == s2);

        System.out.println(s == s5);
        System.out.println(s == s6);
        System.out.println(s == s7);


    }
}
