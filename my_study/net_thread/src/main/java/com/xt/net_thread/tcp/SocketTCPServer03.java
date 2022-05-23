package com.xt.net_thread.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟Socket应用：服务端送消息给客户端
 */
public class SocketTCPServer03 {
    public static void main(String[] args) throws IOException {
//        1.在本机的9999端口监听，等待连接
//        注意：服务器可能会被多个服务器连接，所有serverSocket.accept()可能会返回多个socket对象
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器端：服务器在9999端口监听，等待连接...");
//        2.当没有客户端连接时，程序会阻塞，等待连接...
        //如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();
//        3.获取输入流，读取客户端写入的数据
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String str = reader.readLine();
        System.out.println(str);
//        5.获取socket相关的输出流，向客户端发送信息
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("hello,client 字符流");
        writer.newLine();//插入换行符，表示写入内容结束，注意需要对方使用readLine()结束
        writer.flush();//使用字符流，需要手动刷新，不然不会写入
//        6.关闭流和socket
        writer.close();
        reader.close();
        socket.close();
        serverSocket.close();
        System.out.println("服务器端退出...");
    }
}
