package com.example1._2.demo;

public class Main {

    private static class Solution {

        public static void main(String[] args) {
            System.out.println(new Solution().intToRoman(1994));
        }


        public String intToRoman(int num) {

            StringBuilder sb = new StringBuilder();

            while (num / 1000 != 0) {
                sb.append("M");
                num -= 1000;
            }

            if (num / 900 != 0) {
                sb.append("CM");
                num -= 900;
            }

            while (num / 500 != 0) {
                sb.append("D");
                num -= 500;
            }

            if (num / 400 != 0) {
                sb.append("CD");
                num -= 400;
            }

            while (num / 100 != 0) {
                sb.append("C");
                num -= 100;
            }

            if (num / 90 != 0) {
                sb.append("XC");
                num -= 90;
            }

            while (num / 50 != 0) {
                sb.append("L");
                num -= 50;
            }

            if (num / 40 != 0) {
                sb.append("XL");
                num -= 40;
            }

            while (num / 10 != 0) {
                sb.append("X");
                num -= 10;
            }

            if (num / 9 != 0) {
                sb.append("IX");
                num -= 9;
            }

            while (num / 5 != 0) {
                sb.append("V");
                num -= 5;
            }

            if (num / 4 != 0) {
                sb.append("IV");
                num -= 4;
            }

            while (num > 0) {
                sb.append("I");
                num -= 1;
            }

            return sb.toString();


        }
    }

}
