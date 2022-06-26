package com.example._14.demo.service;

import com.example._14.demo.service.bo.SubmitBo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author 767133
 * @date 2022/6/208:04
 */
public class ExecuteTaskSplit {

    private static final ConcurrentHashMap<Integer, ThreadPoolExecutor> TASK_POOL =
            new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<Integer, Map<String, SubmitBo>> TEMP_CACHE = new ConcurrentHashMap<>();

    private static final BigDecimal DEFAULT;

    private static final ConcurrentHashMap<Integer, BufferedWriter> SPLIT_FILE_DAILY = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, BufferedWriter> SPLIT_FILE_OPEN = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, BufferedWriter> SPLIT_FILE_CALC = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, BufferedWriter> SPLIT_FILE_REPAY = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<Integer, BufferedWriter> SPLIT_FILE_INPUT = new ConcurrentHashMap<>();

    static {
        int count = 100;
        for (int i = 0; i < count; i++) {
            TASK_POOL.putIfAbsent(i, new ThreadPoolExecutor(1, 1,
                    10, TimeUnit.HOURS, new LinkedBlockingQueue<>()));
            TEMP_CACHE.putIfAbsent(i, new HashMap<>());
            try {
                SPLIT_FILE_INPUT.put(i, Files.newBufferedWriter(Paths.get(
                                "C:\\tmp\\20220620\\split\\input_" + i + ".dat"),
                        StandardCharsets.UTF_8
                ));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DEFAULT = new BigDecimal(count);
    }

    public static SubmitBo getSubmitBo(String accountNo) {
        Integer splitId = new BigDecimal(accountNo).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        SubmitBo bo = TEMP_CACHE.get(splitId).get(accountNo);
        if (bo == null) {
            if (TEMP_CACHE.get(splitId).putIfAbsent(accountNo, bo = new SubmitBo(accountNo, splitId)) != null) {
                throw new RuntimeException();
            }
        }
        return bo;
    }

    public static ThreadPoolExecutor getTaskPoolByAccountNo(String accountNo) {
        return TASK_POOL.get(new BigDecimal(accountNo).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue());
    }

    public static void submit(String accountNo, Runnable r) {
        Integer splitId = new BigDecimal(accountNo).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        TASK_POOL.get(splitId).submit(r);
    }

    private static final Map<Integer, List<String[]>> map = new HashMap<>();

    static {
        for (int i = 0; i < 100; i++) {
            map.put(i, new ArrayList<>());
        }
    }

    public static void lazySubmit(String key, String[] data) {

        Integer splitId = new BigDecimal(data[0]).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        List<String[]> param = map.get(splitId);
        param.add(data);
        CountDownLatch latch = new CountDownLatch(1);
        if (param.size() % 100000 == 0) {
            TASK_POOL.get(splitId).submit(() -> {
                List<String[]> input = new ArrayList<>(param);
                latch.countDown();
                for (String[] strings : input) {
                    DataHandler.getWriteByFileName(key).accept(strings);
                }
            });
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            param.clear();
        }


    }


    public static void dailyInfoWrite(String[] data) {
        Integer splitId = new BigDecimal(data[0]).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        BufferedWriter output = SPLIT_FILE_DAILY.get(splitId);
        StringBuilder line = new StringBuilder(data[0]);
        for (int i = 1; i < data.length; i++) {
            line.append(",").append(data[i]);
        }
        try {
            output.write(line.toString());
            output.newLine();
            output.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void openInfoWrite(String[] data) {
        Integer splitId = new BigDecimal(data[0]).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        BufferedWriter output = SPLIT_FILE_OPEN.get(splitId);
        StringBuilder line = new StringBuilder(data[0]);
        for (int i = 1; i < data.length; i++) {
            line.append(",").append(data[i]);
        }
        try {
            output.write(line.toString());
            output.newLine();
            output.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void calcInfoWrite(String[] data) {
        Integer splitId = new BigDecimal(data[0]).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        BufferedWriter output = SPLIT_FILE_CALC.get(splitId);
        StringBuilder line = new StringBuilder(data[0]);
        for (int i = 1; i < data.length; i++) {
            line.append(",").append(data[i]);
        }
        try {
            output.write(line.toString());
            output.newLine();
            output.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void repayInfoWrite(String[] data) {
        Integer splitId = new BigDecimal(data[0]).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        BufferedWriter output = SPLIT_FILE_REPAY.get(splitId);
        StringBuilder line = new StringBuilder(data[0]);
        for (int i = 1; i < data.length; i++) {
            line.append(",").append(data[i]);
        }
        try {
            output.write(line.toString());
            output.newLine();
            output.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static final ThreadLocal<Counter> count = ThreadLocal.withInitial(Counter::new);
    private static final int FLUSH_COUNT = 2000;

    public static void inputInfoWrite(String[] data) {
        Integer splitId = new BigDecimal(data[0]).divideAndRemainder(DEFAULT)[1].toBigInteger().intValue();
        BufferedWriter output = SPLIT_FILE_INPUT.get(splitId);
        StringBuilder line = new StringBuilder(data[0]);
        for (int i = 1; i < data.length; i++) {
            line.append(",").append(data[i]);
        }
        try {
            output.write(line.toString());
            output.newLine();
            count.get().add();
            if (count.get().get() % FLUSH_COUNT == 0) {
                output.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static class Counter {
        private int count = 0;

        public void add() {
            count += 1;
        }

        public int get() {
            return count;
        }
    }
}