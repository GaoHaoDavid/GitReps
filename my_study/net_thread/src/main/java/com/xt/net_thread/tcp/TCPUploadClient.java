package com.xt.net_thread.tcp;

import com.xt.net_thread.utils.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TCPUploadClient {
    public static void main(String[] args) throws IOException {
        //1.创建客户端连接，得到一个socket
        System.out.println("TCPUploadClient...");
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //2.创建读取磁盘的输入流
        String filePath = "C:\\Users\\GaoHao\\Pictures\\证件照\\beautiful.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //3.获取socket输出流，将byte数组发送到服务端
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);
        System.out.println("发送完成...");
        socket.shutdownOutput();
        //接受从服务端回复的消息
        InputStream inputStream = socket.getInputStream();
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);
        //4.关闭流
        bis.close();
        bos.close();
        socket.close();
    }
}
