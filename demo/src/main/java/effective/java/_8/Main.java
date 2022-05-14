package effective.java._8;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final List<Demo> test = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            new Demo1();
            Thread.onSpinWait();
        }
    }
}

class Demo {

}


class Demo1 extends Demo {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

class Demo2 extends Demo{
    @Override
    protected void finalize() throws Throwable {
        Main.test.add(this);
        super.finalize();
    }
}

