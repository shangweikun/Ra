package com.example._12.demo;

public class MainA {

    public Class<?> test0(){
        return new Runnable(){
            @Override
            public void run() {
            }
        }.getClass();
    }

    public static Class<?> test1(){
        return new Runnable(){
            @Override
            public void run() {
            }
        }.getClass();
    }

    public static void main(String[] args) {
        MainA mainA0 = new MainA();
        MainA mainA1 = new MainA();

        System.out.println(mainA0.test0().equals(mainA1.test0()));
        System.out.println(mainA0.test1().equals(mainA1.test1()));
    }
}
