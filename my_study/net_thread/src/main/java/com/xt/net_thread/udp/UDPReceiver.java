package com.xt.net_thread.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 接收端：
 * 在9999端口进行监听，如果有数据报发送到本机的9999端口，就会接收到该数据。否则程序会进入到阻塞状态
 */
public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        //1.创建一个DatagramSocket对象，准备在9999端口接收数据
        System.out.println("监听9999端口...");
        DatagramSocket socket = new DatagramSocket(9999);
        //2.构建一个DatagramPacket对象，准备接收数据
        //UDP最大数据包为64k
        byte[] buf = new byte[64 * 1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        //3.调用接收方法，将通过网络传输的DatagramPacket对象，填充到packet对象
        System.out.println("接收端等待接受数据...");
        socket.receive(packet);
        //4.把packet进行拆包，取出数据，进行显示
        int length = packet.getLength();//实际接受到的数据长度
        byte[] data = packet.getData();//收到的数据
        String s = new String(data, 0, length);
        System.out.println("接收到的数据：" + s);
        //5.回复信息给发送端
        byte[] msg = "好的明天见".getBytes();
        packet = new DatagramPacket(msg, msg.length, InetAddress.getByName("192.168.1.101"), 9998);
        socket.send(packet);
        //6.关闭连接
        socket.close();
        System.out.println("关闭连接...");


    }

}
