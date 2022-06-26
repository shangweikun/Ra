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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    private static final String DEFAULT_SWITCH_FILE_PATH = "/mnt/c/giteeSpace/20220626/switch.dat";
    private static final String DEFAULT_PATH = "/mnt/c/giteeSpace/20220626/";
    private static final Executor executor = Executors.newSingleThreadExecutor();

    private static final List<InfoHandle> handlers = new ArrayList<>();

    static {
        handlers.add(new DateInfoHandle());
        handlers.add(new StartInfo());
        handlers.add(new CloseInfo());
    }


    //todo 主线程负责执行命令；单一子线程负责格式化结果
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
                String switchPath = args.length >= 2 ? args[1] : DEFAULT_SWITCH_FILE_PATH;

                boolean loopSwitch;

                latch.await();

                String fileName = args.length > 0 ? args[0] : "test.txt";
                if (StrUtil.isEmpty(fileName)) {
                    throw new NullPointerException("目标文件不能为空");
                }
                BufferedReader reader = Files.newBufferedReader(
                        Paths.get(DEFAULT_PATH + fileName)
                );

                do {
                    while (reader.ready()) {

                        String line = reader.readLine();
                        Info info = new Info();

                        //todo 按照时间拆分组
                        //todo 按组提交
                        handlers.forEach(handler -> handler.handle(info, List.of(line)));
                    }
                    Thread.sleep(1000L);
                    loopSwitch = Boolean.parseBoolean(Files.readAllLines(Paths.get(switchPath)).get(0));
                } while (loopSwitch);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        System.out.println("process end");
    }
}
