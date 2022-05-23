package com.xt.net_thread.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟Socket应用：服务端送消息给客户端
 */
public class SocketTCPServer02 {
    public static void main(String[] args) throws IOException {
//        1.在本机的9999端口监听，等待连接
//        注意：服务器可能会被多个服务器连接，所有serverSocket.accept()可能会返回多个socket对象
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器端：服务器在9999端口监听，等待连接...");
//        2.当没有客户端连接时，程序会阻塞，等待连接...
        //如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
        System.out.println("服务器端：tcp=" + socket.getClass());
//        3.获取输入流，读取客户端写入的数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }
//        5.获取socket相关的输出流，向客户端发送信息
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes());
        //设置结束标记
        socket.shutdownOutput();
//        6.关闭流和socket
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
