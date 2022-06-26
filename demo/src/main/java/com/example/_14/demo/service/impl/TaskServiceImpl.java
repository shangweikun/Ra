package com.example._14.demo.service.impl;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import com.example._14.demo.service.DataHandler;
import com.example._14.demo.service.ExecuteTaskSplit;
import com.example._14.demo.service.TaskService;
import org.apache.commons.compress.utils.Charsets;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @author 767133
 * @date 2022/6/208:14
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Map<String, String> NAME_M_PATH = MapUtil.unmodifiable(
            MapUtil.<String, String>builder()
                    .put("input.dat", "C:\\tmp\\20220620\\input.dat")
                    .build()
    );

    @Override
    public boolean submitTask() {
        NAME_M_PATH.forEach((key, value) ->
                DataHandler.TASK_POOL.get(key).submit(() -> {
//                    try (BufferedReader reader = Files.newBufferedReader(Paths.get(value), StandardCharsets.UTF_8)) {
                    StopWatch watch = new StopWatch();
                    watch.start();
                    List<String> input = FileUtil.readLines(new File(value), Charset.defaultCharset());
                    try {
                        watch.stop();
                        System.out.println(watch.getTotalTimeMillis());
                        watch = new StopWatch();
                        watch.start();
                        for (String line : input) {
                            String[] data = line.split(",");
                            ExecuteTaskSplit.lazySubmit(key, data);
                        }
//                        while (reader.ready()) {
//                            String[] data = reader.readLine().split(",");
//                            ExecuteTaskSplit.submit(data[0],
//                                    () -> DataHandler.getWriteByFileName(key).accept(data));
//                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    watch.stop();
                    System.out.println(watch.getTotalTimeMillis());
                    System.out.println(key + " end");
                }));
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new TaskServiceImpl().submitTask());
    }
}
