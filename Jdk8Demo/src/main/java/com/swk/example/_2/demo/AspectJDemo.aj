package com.swk.example._2.demo;

public aspect AspectJDemo {

    pointcut say():
            execution(* App.say(..));
    before(): say() {
        System.out.println("AspectJDemo before say");
    }
    after(): say() {
        System.out.println("AspectJDemo after say");
    }

}
