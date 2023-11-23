package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<byte[]> tmp = new ArrayList<>();
        while (true) {

            for (int i = 0; i < 500; i++) {
                byte[] bytes = new byte[1024 * 1024];
                tmp.add(bytes);
            }
            Thread.sleep(1000L);

            if (tmp.size() > 1000){
                tmp.clear();
            }
        }

    }
}