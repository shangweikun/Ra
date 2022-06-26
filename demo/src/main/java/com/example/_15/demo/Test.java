package com.example._15.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test {

    private static final Executor exe = Executors.newSingleThreadExecutor();
    private static boolean loopSwitch = true;

    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("ping 127.0.0.1 -t");
            exe.execute(() -> {
                BufferedReader tests = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));

                while (loopSwitch) {
                    try {
                        while (tests.ready()) {
                            System.out.println(tests.readLine());
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("process started");
            });
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
