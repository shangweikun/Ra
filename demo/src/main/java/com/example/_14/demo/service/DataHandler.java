package com.example._14.demo.service;

import cn.hutool.core.lang.SimpleCache;
import com.example._14.demo.service.bo.SubmitBo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author 767133
 * @date 2022/6/208:04
 */
public class DataHandler {

    public static final File outFile =
            new File("C:\\Users\\user\\Documents\\工作文档 · 改\\程序员大赛\\6月\\output.dat");

    //todo remove
    public static final ConcurrentHashMap<String, ThreadPoolExecutor> TASK_POOL = new ConcurrentHashMap<>();

    static {
        TASK_POOL.putIfAbsent("daily_info.dat", new ThreadPoolExecutor(1, 1,
                10, TimeUnit.HOURS, new ArrayBlockingQueue<>(1),
                r -> new Thread(r, "daily_info - pool - thread - 1")));
        TASK_POOL.putIfAbsent("open.dat", new ThreadPoolExecutor(1, 1,
                10, TimeUnit.HOURS, new ArrayBlockingQueue<>(1),
                r -> new Thread(r, "open - pool - thread - 1")));
        TASK_POOL.putIfAbsent("calc.dat", new ThreadPoolExecutor(1, 1,
                10, TimeUnit.HOURS, new ArrayBlockingQueue<>(1),
                r -> new Thread(r, "calc - pool - thread - 1")));
        TASK_POOL.putIfAbsent("repay.dat", new ThreadPoolExecutor(1, 1,
                10, TimeUnit.HOURS, new ArrayBlockingQueue<>(1),
                r -> new Thread(r, "repay - pool - thread - 1")));
        TASK_POOL.putIfAbsent("input.dat", new ThreadPoolExecutor(1, 1,
                10, TimeUnit.HOURS, new ArrayBlockingQueue<>(1),
                r -> new Thread(r, "input - pool - thread - 1")));
    }

    private static final SimpleCache<String, BufferedWriter> cache = new SimpleCache<>();

    private static void writeToFile(SubmitBo todo) throws IOException {
        System.out.println("writeToFile : " + todo.id);
        BufferedWriter output = cache.get("output", () -> new BufferedWriter(new FileWriter(outFile)));
        String line = todo.toLineString();
        output.write(line);
        output.flush();
        TASK_POOL.remove(todo.id);
    }


    private static void dailyInfoHandle(String[] data) {
        try {
            SubmitBo todo = ExecuteTaskSplit.getSubmitBo(data[0]);
            todo.setColumn2(data[1]);
            todo.setColumn7(data[2]);
            todo.setColumn8(data[3]);
            todo.setColumn10(data[4]);
            todo.setColumn11(new BigDecimal(todo.getColumn11() == null ? "0" : todo.getColumn11())
                    .add(new BigDecimal(data[3]).multiply(new BigDecimal(data[6])))
                    .toString());
            todo.setColumn13(data[5]);
            todo.handle(8);
            if (todo.isHandled()) {
                System.out.println("todo write [dailyInfoHandle] : " + todo.id);
                writeToFile(todo);
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    private static void openInfoHandle(String[] data) {
        try {
            SubmitBo todo = ExecuteTaskSplit.getSubmitBo(data[0]);

            todo.setColumn3(data[3]);
            todo.setColumn4(data[4]);
            todo.setColumn5(data[5]);
            todo.setColumn6(data[6]);
            todo.handle(4);
            if (todo.isHandled()) {
                System.out.println("todo write [openInfoHandle] : " + todo.id);
                try {
                    writeToFile(todo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    private static void calcInfoHandle(String[] data) {
        try {
            SubmitBo todo = ExecuteTaskSplit.getSubmitBo(data[0]);
            todo.setColumn9(new BigDecimal(todo.getColumn9() == null ? "0" : todo.getColumn9())
                    .add(new BigDecimal(data[1]))
                    .toString());
            todo.setColumn12(new BigDecimal(todo.getColumn12() == null ? "0" : todo.getColumn12())
                    .add(new BigDecimal(data[2]))
                    .toString());
            todo.handle(2);
            if (todo.isHandled()) {
                System.out.println("todo write [calcInfoHandle] : " + todo.id);
                writeToFile(todo);
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    private static void repayInfoHandle(String[] data) {
        try {
            SubmitBo todo = ExecuteTaskSplit.getSubmitBo(data[0]);

            todo.setColumn9(new BigDecimal(todo.getColumn9() == null ? "0" : todo.getColumn9())
                    .negate()
                    .add(new BigDecimal(data[4]))
                    .negate()
                    .toString());
            todo.setColumn11(new BigDecimal(todo.getColumn11() == null ? "0" : todo.getColumn11())
                    .negate()
                    .add(new BigDecimal(data[5]))
                    .negate()
                    .toString());
            todo.setColumn12(new BigDecimal(todo.getColumn12() == null ? "0" : todo.getColumn12())
                    .negate()
                    .add(new BigDecimal(data[6]))
                    .negate()
                    .toString());
            todo.setColumn14(data[7]);
            todo.setColumn15(new BigDecimal(data[1]).add(new BigDecimal(data[2])).toString());
            todo.setColumn16(new BigDecimal(data[3]).add(new BigDecimal(data[4])).toString());
            todo.setColumn17(new BigDecimal(data[5]).add(new BigDecimal(data[6])).toString());
            todo.handle(1);
            if (todo.isHandled()) {
                System.out.println("todo write [repayInfoHandle] : " + todo.id);
                writeToFile(todo);
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }

    public static Consumer<String[]> getHandleByFileName(String fileName) {
        return switch (fileName) {
            case "daily_info.dat" -> DataHandler::dailyInfoHandle;
            case "open.dat" -> DataHandler::openInfoHandle;
            case "calc.dat" -> DataHandler::calcInfoHandle;
            case "repay.dat" -> DataHandler::repayInfoHandle;
            default -> throw new RuntimeException();
        };
    }


    public static Consumer<String[]> getWriteByFileName(String fileName) {
        return switch (fileName) {
            case "daily_info.dat" -> ExecuteTaskSplit::dailyInfoWrite;
            case "open.dat" -> ExecuteTaskSplit::openInfoWrite;
            case "calc.dat" -> ExecuteTaskSplit::calcInfoWrite;
            case "repay.dat" -> ExecuteTaskSplit::repayInfoWrite;
            case "input.dat" -> ExecuteTaskSplit::inputInfoWrite;
            default -> throw new RuntimeException();
        };
    }


}
