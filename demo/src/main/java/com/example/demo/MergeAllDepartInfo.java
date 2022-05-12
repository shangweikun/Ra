package com.example.demo;

import java.io.*;
import java.util.List;

public class MergeAllDepartInfo {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\shang\\Documents\\工作空间\\depart.info");
        File file0 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart0.info");
        File file1 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart1.info");
        File file2 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart2.info");
        File file3 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart3.info");
        File file4 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart4.info");
        File file6 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart6.info");
        File file7 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart7.info");
        File file8 = new File("C:\\Users\\shang\\Documents\\工作空间\\depart8.info");

        List<File> files = List.of(file, file0, file1, file2, file3,
                file4, file6, file7, file8);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("C:\\Users\\shang\\Documents\\工作空间\\result.info")
        ));
        files.forEach(f -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(f)
            ))) {
                while (reader.ready()) {
                    writer.write(reader.readLine());
                    writer.newLine();
                    writer.flush();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
