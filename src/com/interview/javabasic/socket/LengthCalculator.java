package com.interview.javabasic.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LengthCalculator extends Thread {
    // 以socket为成员变量
    private Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 获取socket的输入流
            InputStream inputStream = socket.getInputStream();
            // 获取socket的输出流
            OutputStream outputStream = socket.getOutputStream();

            // buff主要用来读取输入的内容，存储为byte数组，ch主要用来获取读取数组的长度
            int ch = 0;
            byte [] buff = new byte[1024];
            ch = inputStream.read(buff);
            // 将输入流的字符数组转换为字符串，这里获取的内容是客户端发来的字符串参数
            String content = new String(buff, 0, ch);
            System.out.println(content);
            // 往输出流中写入获得的字符串的长度，回发给客户端
            outputStream.write(String.valueOf(content.length()).getBytes());
            // 关闭输入输出流和socket
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
