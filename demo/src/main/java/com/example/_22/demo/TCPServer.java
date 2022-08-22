package com.example._22.demo;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {

        try (ServerSocket service = new ServerSocket(8080);
             Socket socket = service.accept();
             InputStream is = socket.getInputStream();
             OutputStream os = socket.getOutputStream()) {

            byte[] bytes = new byte[1024];
            int length;
            while ((length = is.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, length));
            }

            socket.shutdownInput();

            System.out.println("receive message from client");

            os.write("hello client".getBytes());
            socket.shutdownOutput();
        }
    }
}
