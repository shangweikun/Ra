package com.example._22.demo;

import org.apache.flink.streaming.runtime.io.InputGateUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 8080);
             OutputStream os = socket.getOutputStream();
             InputStream in = socket.getInputStream()) {

            os.write("hello Server".getBytes());
            socket.shutdownOutput();

            System.out.println("send to Server , wait message from server");

            byte[] bytes = new byte[1024];
            int length;
            while ((length = in.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }

            socket.shutdownInput();
        }
    }
}
