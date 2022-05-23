package com.xt.net_thread.tcp;

import com.xt.net_thread.utils.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPUploadServer {
    public static void main(String[] args) throws IOException {
        //1.服务端在本机监听8888
        System.out.println("开始监听8888端口...");
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.等待连接
        Socket socket = serverSocket.accept();
        //3.读取客户端发送的数据
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        //4.将得到的byte数组写入到指定的路径
        String fileName = "C:\\Users\\GaoHao\\Desktop\\Text\\Idea-projects\\my_study\\net_thread\\src\\main\\resources\\imgs\\beautiful.jpg";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
        bos.write(bytes);
        //向客户端回复
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("服务器端收到图片");
        writer.flush();//将内容刷新到数据通道
        socket.shutdownOutput();//设置写入结束标记
        //5.关闭流
        bis.close();
        bos.close();
        serverSocket.close();
        System.out.println("收到图片，程序结束...");
    }
}
