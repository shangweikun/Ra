package com.example._30.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * 匹配 ${} 参数信息
     *
     * @param args
     */
    public static void main(String[] args) {

        String str = "124${1a23}sf";

        String pattern = "(?<=\\$\\{)([A-Za-z0-9]+)(?=})";

        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(str);

        for (String s : str.split(pattern)
        ) {
            System.out.println(s);
        }

        System.out.println(m.find());

    }
}
