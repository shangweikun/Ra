package com.example.demo;

import java.io.*;

public class MergeFileDemo {

    public static void main(String[] args) {
        File file1 = new File("C:\\Users\\shang\\Documents\\工作空间\\支行信息");
        File file2 = new File("C:\\Users\\shang\\Documents\\工作空间\\支行信息翻译");

        try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(
                new FileInputStream(file1)));
             BufferedReader reader2 = new BufferedReader(new InputStreamReader(
                     new FileInputStream(file2)));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                     new FileOutputStream("C:\\Users\\shang\\Documents\\工作空间\\depart.info")
             ))) {
            while (reader1.ready() && reader2.ready()) {
                String line1 = reader1.readLine();
                String line2 = reader2.readLine();
                writer.write(line1 + line2);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
