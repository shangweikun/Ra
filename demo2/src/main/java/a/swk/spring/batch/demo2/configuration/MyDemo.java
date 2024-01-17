package a.swk.spring.batch.demo2.configuration;

public class MyDemo {

    public static ThreadLocal<String> LOCAL =
            new InheritableThreadLocal<>();
}
