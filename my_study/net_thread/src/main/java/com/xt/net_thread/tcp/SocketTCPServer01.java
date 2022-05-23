package com.xt.net_thread.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟Socket应用：客户端发送消息给服务端
 */
public class SocketTCPServer01 {
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
//        4.关闭流和socket
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
