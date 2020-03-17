package com.interview.javabasic.socket;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        // 客户端发数据报给服务端
        DatagramSocket socket = new DatagramSocket();
        // 要发给服务端的数据
        byte[] buff = "UDPDatagramSocket".getBytes();
        // 将IP地址封装成InetAddress对象
        InetAddress address = InetAddress.getByName("127.0.0.1");
        // 将要发送给服务器的信息封装成数据报
        DatagramPacket packet = new DatagramPacket(buff, buff.length, address, 65001);
        // 发送数据报给服务端
        socket.send(packet);

        // 客户端接收服务端发送过来的数据报
        byte[] data = new byte[100];
        // 创建DatagramPacket用来存储服务器端发来的数据报
        DatagramPacket receivedPacket = new DatagramPacket(data, data.length);
        // 将接收到的数据存储到DatagramPacket对象中
        socket.receive(receivedPacket);
        // 将服务器端发送的数据取出，打印到控制台
        String content = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
        System.out.println(content);
    }
}
