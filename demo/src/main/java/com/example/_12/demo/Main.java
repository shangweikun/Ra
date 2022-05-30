package com.example._12.demo;

public class Main {

    class Sub {
        String string = "sub";
    }

    public static void main(String[] args) {
        Main main0 = new Main();
        Main main1 = new Main();
        System.out.println(
                (main0.new Sub()).getClass()
                        .equals((main1.new Sub()).getClass()));
    }
}
