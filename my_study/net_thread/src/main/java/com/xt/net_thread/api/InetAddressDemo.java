package com.xt.net_thread.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {

//        1.获取当前主机的对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
//        2.根据主机名获取对象
        InetAddress host1 = InetAddress.getByName("DESKTOP-EAT6PPM");
        System.out.println(host1);
//        3.根据域名返回对象，例如www.baidu.com
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println(host2);
//        通过该对象获取对应的ip地址和主机名
        String hostAddress = host2.getHostAddress();
        String hostName = host2.getHostName();
        System.out.println("hostAddress：" + hostAddress);
        System.out.println("hostName：" + hostName);
    }
}
