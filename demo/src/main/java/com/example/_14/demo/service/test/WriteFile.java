package com.example._14.demo.service.test;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Snowflake;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WriteFile {

    public static final File outFile =
            new File("C:\\tmp\\20220620\\output.dat");


    public static void main(String[] args) {

        Snowflake snowflake = new Snowflake(1, 1);
        try (BufferedWriter writer = Files.newBufferedWriter(
                Paths.get("C:\\tmp\\20220620\\output.dat"))) {
            StopWatch watch = new StopWatch();
            watch.start();
            for (int i = 1; i <= 100_000_000; i++) {
                writer.write(snowflake.nextIdStr() + "," + "0" + "0");
                writer.newLine();
                if (i % 100 == 0) {
                    writer.flush();
                }
            }
            watch.stop();
            System.out.println(watch.getTotalTimeMillis());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
