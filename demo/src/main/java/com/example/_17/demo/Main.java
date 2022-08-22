package com.example._17.demo;

import cn.hutool.core.util.ReflectUtil;

public class Main {

    public static final String name;

    static {
        try {
            name = (String) Test.class.getMethod("name").getDefaultValue();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    private final Object demo = new Object();

    @Test
    private final Object demo1 = new Object();

    public static void main(String[] args) throws NoSuchMethodException {
        String a = Test.name;
        String b = ReflectUtil.getField(Main.class, "demo").getAnnotation(Test.class).name();
        String c = ReflectUtil.getField(Main.class, "demo1").getAnnotation(Test.class).name();
        String d = name;

        System.out.println(a == d);
        System.out.println(a == b);


//        System.out.println(System.identityHashCode(name));
//        System.out.println(System.identityHashCode(
//                Test.class.getMethod("name").getDefaultValue())
//        );

        System.out.println(System.identityHashCode(Test.name));
        System.out.println(System.identityHashCode(
                ReflectUtil.getField(Main.class, "demo").getAnnotation(Test.class).name())
        );

    }


}
