package com.example._15.demo;

import cn.hutool.core.util.StrUtil;
import com.example._15.demo.handlers.Info;
import com.example._15.demo.handlers.InfoHandle;
import com.example._15.demo.handlers.impl.CloseInfo;
import com.example._15.demo.handlers.impl.DateInfoHandle;
import com.example._15.demo.handlers.impl.StartInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    private static final String DEFAULT_PATH = "/mnt/c/giteeSpace/20220626/";
    private static final Executor executor = Executors.newSingleThreadExecutor();
    private static final List<InfoHandle> handlers = new ArrayList<>();

    static {
        handlers.add(new DateInfoHandle());
        handlers.add(new StartInfo());
        handlers.add(new CloseInfo());
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        Process process;
        try {
            process = Runtime.getRuntime().exec("ping 127.0.0.1 -t");
            System.out.println("process started");
            latch.countDown();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.execute(() -> {

            try {

                latch.await();

                String fileName = args.length > 0 ? args[0] : "test.txt";
                if (StrUtil.isEmpty(fileName)) {
                    throw new NullPointerException("目标文件不能为空");
                }
                BufferedReader reader = Files.newBufferedReader(
                        Paths.get(DEFAULT_PATH + fileName)
                );
                String time = null;
                ArrayList<String> cache = new ArrayList<>();
                while (true) {
                    while (reader.ready()) {

                        String line = reader.readLine();
                        String temp = line.substring(0, 10);
                        if (time == null) {
                            time = temp;
                        } else if (!Objects.equals(time, temp)) {
                            time = temp;
                            Info info = new Info();
                            handlers.forEach(handler -> handler.handle(info, List.of(line)));
                            System.out.println(info);
                            cache.clear();
                        }
                        cache.add(line);
                    }
                    Thread.sleep(1000L);
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        System.out.println("process end");
    }
}
