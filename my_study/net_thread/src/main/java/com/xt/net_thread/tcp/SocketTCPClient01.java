package com.xt.net_thread.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 模拟Socket应用：客户端发送消息给服务端
 */
public class SocketTCPClient01 {
    public static void main(String[] args) throws IOException {

//        1.连接服务端（ip，端口）
//        连接本机的9999端口，如果连接成功，则返回一个socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端：tcp=" + socket.getClass());
//        2.连接上后，生成Socket，拿到一个跟socket对象相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
//        3.通过输出流，写入数据到数据通道
        outputStream.write("hello,server".getBytes());
//        4.关闭输出流
        outputStream.close();
        System.out.println("客户端退出...");


    }
}
