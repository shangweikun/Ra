package effective.java._8;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final List<effective.java._8.Demo> test = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            new Demo1();
            Thread.onSpinWait();
        }
    }

    static class Demo extends effective.java._8.Demo implements AutoCloseable {

        @Override
        public void close() throws Exception {

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

class Demo2 extends Demo {
    @Override
    protected void finalize() throws Throwable {
        Main.test.add(this);
        super.finalize();
    }
}

