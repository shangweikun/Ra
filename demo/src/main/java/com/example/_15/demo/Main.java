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

	private static final String DEFAULT_PATH = "/Users/swk/giteePath/";
	private static final Executor executor = Executors.newSingleThreadExecutor();
	private static final List<InfoHandle> handlers = new ArrayList<>();

	static {
		handlers.add(new StartInfo());
		handlers.add(new CloseInfo());
		handlers.add(new DateInfoHandle());
	}


	public static void main(String[] args) throws IOException, InterruptedException {

		String workSpace = args.length < 1 ? DEFAULT_PATH : args[0];

		CountDownLatch latch = new CountDownLatch(1);
		Process process;
//		try {
//			process = Runtime.getRuntime().exec("sudo tcpdump -v > " + workSpace + "test.txt");
		System.out.println("process started");
		latch.countDown();
//			process.waitFor();
//		} catch (IOException | InterruptedException e) {
//			throw new RuntimeException(e);
//		}

		executor.execute(() -> {

			BufferedReader reader = null;
			try {

				latch.await();

				String filePath = workSpace + "test.txt";
				if (StrUtil.isEmpty(filePath)) {
					throw new NullPointerException("目标文件不能为空");
				}
				reader = Files.newBufferedReader(
						Paths.get(filePath)
				);
				String time = null;
				ArrayList<String> cache = new ArrayList<>();

				System.out.print("|date|");
				for (InfoHandle handler : handlers) {
					System.out.print(handler.getColumnName());
					System.out.print("|");
				}
				System.out.println();

				String line = "";
				while (true) {
					while (reader.ready()) {

						String tmp = (reader.readLine() + " ");
						if (tmp.substring(0, 2).matches("[0-9]+")) {
							line = tmp;
							continue;
						} else {
							line += tmp;
						}

						String temp = line.substring(0, 8);
						if (time == null) {
							time = temp;
						} else if (!Objects.equals(time, temp)) {
							time = temp;
							Info info = new Info();
							handlers.forEach(handler -> handler.handle(info, cache));
							System.out.println(temp + info);
							cache.clear();
						}
						cache.add(line);
						line = "";
					}
					Thread.sleep(1000L);
				}
			} catch (IOException | InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});

		System.out.println("process end");
	}
}
