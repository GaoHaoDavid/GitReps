package com.xt.net_thread.udp;

import java.io.IOException;
import java.net.*;

/**
 * 发送端:
 * 向客户端发送数据
 * 发送端也可以是接收端
 */
public class UDPSender {
    public static void main(String[] args) throws IOException {
        //1.创建一个DatagramSocket对象，准备在9999端口接收数据和发送数据
        System.out.println("发送数据...");
        DatagramSocket socket = new DatagramSocket(9998);
        //2.将需要发送的数据，封装到DatagramPacket中
        byte[] data = "hello,明天一起吃火锅".getBytes();
        //封装的DatagramPacket对象，需要有发送内容的字节数组，数据长度，主机（ip），接收方的端口号
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.101"), 9999);
        socket.send(packet);
        //3.接收接收端的回复信息
        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        int length = packet.getLength();//实际接受到的数据长度
        byte[] msg = packet.getData();//收到的数据
        String s = new String(msg, 0, length);
        System.out.println("回复信息：" + s);
        //4.关闭资源
        socket.close();
        System.out.println("发送方结束...");
    }
}
