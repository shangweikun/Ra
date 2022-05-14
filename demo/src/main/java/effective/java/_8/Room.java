package effective.java._8;

public class Room implements AutoCloseable {

    private final String number;

    public Room(String number) {
        this.number = number;
    }

    public static void main(String[] args) throws InterruptedException {
        test();
        test2();
        test1();
        int count = 10;
        while (count-- > 0) {
            System.gc();
            Thread.sleep(1000L);
        }

    }

    private static void test() {
        new Room("1");
    }

    private static void test2() {
        Runnable runnable = () -> new Room("2");
        new Thread(runnable).start();
    }

    private static void test1() {
        try (Room room = new Room("99")) {
            System.out.println("!");
        }
    }

    @Override
    public void close() {
        System.out.println("I'm cleaning room - " + number + " now");
    }

}
