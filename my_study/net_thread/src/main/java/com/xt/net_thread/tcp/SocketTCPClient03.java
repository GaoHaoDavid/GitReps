package com.xt.net_thread.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 模拟Socket应用：服务端送消息给客户端
 */
public class SocketTCPClient03 {
    public static void main(String[] args) throws IOException {

//        1.连接服务端（ip，端口）
//        连接本机的9999端口，如果连接成功，则返回一个socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端：tcp=" + socket.getClass());
//        2.连接上后，生成Socket，拿到一个跟socket对象相关联的输出流
        OutputStream outputStream = socket.getOutputStream();
//        3.通过输出流，写入数据到数据通道，使用字符流
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write("hello,server 字符流");
        writer.newLine();//插入换行符，表示写入内容结束，注意需要对方使用readLine()结束
        writer.flush();//使用字符流，需要手动刷新，不然不会写入
//        4.获取和socket相关的输入流
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String str = reader.readLine();
        System.out.println(str);
//        5.关闭输出流
        writer.close();
        reader.close();
        socket.close();
        System.out.println("客户端退出...");
    }
}
