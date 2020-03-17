package com.interview.javabasic.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        // 服务端接收客户端发送的数据报
        DatagramSocket socket = new DatagramSocket(65001);
        // 存储从客户端接收到的内容
        byte[] buff = new byte[100];
        DatagramPacket packet = new DatagramPacket(buff, buff.length);
        // 接收从客户端发送过来的内容，并将内容封装进DatagramPacket对象中
        socket.receive(packet);

        // 从DatagramPacket对象中获取真正存储的数据
        byte[] data = packet.getData();
        // 将数据从二进制转换为字符串形式
        String content = new String(data, 0 ,packet.getLength());
        System.out.println(content);

        // 将要发送给客户端的数据转换为二进制
        byte[] sendContent = String.valueOf(content.length()).getBytes();
        // 服务端给客户端发送数据报
        // 从DatagramPacket对象中获取数据的来源地址与端口号
        DatagramPacket packetToClient = new DatagramPacket(sendContent, sendContent.length, packet.getAddress(), packet.getPort());
        // 发送数据给客户端
        socket.send(packetToClient);
    }
}
