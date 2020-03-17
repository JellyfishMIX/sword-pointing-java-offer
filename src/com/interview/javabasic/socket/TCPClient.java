package com.interview.javabasic.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        // 创建socket，并指定连接的是本机端口号为65000的服务器socket
        Socket socket = new Socket("127.0.0.1", 65000);
        // 获取输入流
        InputStream inputStream = socket.getInputStream();
        // 获取输出流
        OutputStream outputStream = socket.getOutputStream();
        // 将要传递给Server的字符串转化成byte数组，并将数组写入输出流中
        outputStream.write(new String("websocketTest").getBytes());

        // 主要用来读取输入的内容，存为byte数组，ch主要用来获取读取数组的长度
        int ch = 0;
        byte[] buff = new byte[1024];
        ch = inputStream.read(buff);
        // 将接收流的byte数组转换为字符串，这里是从服务端回发的字符串参数的长度
        String content = new String(buff, 0, ch);
        System.out.println(content);

        // 关闭输入输出流和socket
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
