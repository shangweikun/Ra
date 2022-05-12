package com.example.demo;

import cn.hutool.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class GoogleTranslateDemo {

    private static final Map<String, String> LANGUAGE_MAP = new HashMap<>();

    public static String translate(String langFrom, String langTo, String word) throws Exception {

        Future<String> future = pool.submit(() -> {
            String url =
                    "https://translate.googleapis.com/translate_a/single?" +
                            "client=gtx" +
                            "&sl=" + langFrom +
                            "&tl=" + langTo +
                            "&dt=t&q=" + URLEncoder.encode(word, StandardCharsets.UTF_8);

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", getAgent());


            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        });
        String result;
        try {
            result = future.get(10L, TimeUnit.SECONDS);
        } catch (Exception e) {
            result = null;
        }

        return result == null ? result : parseResult(result);
    }

    private static final Random random = new Random();

    private static String getAgent() {
        return switch (random.nextInt(1, 5)) {
            case 1 ->
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36";
            case 2 ->
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36";
            case 3 -> "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0";
            case 4 ->
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36";
            default ->
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.164 Safari/537.36";
        };
    }

    private static String parseResult(String inputJson) throws Exception {

        final JSONArray jsonArray = new JSONArray(inputJson);
        JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
        String result = "";

        for (var i = 0; i < jsonArray2.size(); i++) {
            result += ((JSONArray) jsonArray2.get(i)).get(0).toString();
        }

        return result;
    }

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(
            1, 1, 10L, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>()
    );

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\shang\\Documents\\工作空间\\支行信息");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file)));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream("C:\\Users\\shang\\Documents\\工作空间\\depart.info")
             ))) {
            while (reader.ready()) {
                String line = reader.readLine();
                System.out.println("line：" + line);
                int count = 0;
                String result = null;
                while (count < 10) {
                    Thread.sleep(new Random().nextLong(30000L, 40000L));
                    result = translate("zh-CN", "en", line);
                    if (result == null) {
                        count++;
                        System.out.println(line + "failure, count is " + count);
                    } else {
                        break;
                    }
                }
                writer.write(line + "|" + (result == null ? "失败" : result));
                writer.newLine();
                writer.flush();
                System.out.println("result：" + result);
            }
        }
    }

    static class Test {

        public static void main(String[] args) throws IOException {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("C:\\Users\\shang\\Documents\\工作空间\\depart.info")
            ));
            writer.write("123");
            writer.newLine();
            writer.write("456");
            writer.flush();
            writer.close();
        }
    }

}
