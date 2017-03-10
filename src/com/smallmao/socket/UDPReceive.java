package com.smallmao.socket;
/**
 * UDP使用数据包的形式出现,需要使用一下两个类
 * 1.数据报的内容:DatagramPacket
 * 2.发送和接收数据报:DatagramSocket
 * UDP是先运行客户端,之后再运行服务器端
 * TCP是先要有服务器端,再进行客户端开发
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收步骤：
 * 使用 DatagramSocket(int port) 建立socket（套间字）服务。（我们注意到此服务即可以接收，又可以发送），port指定监视接受端口。
 * 定义一个数据包（DatagramPacket），储存接收到的数据，使用其中的方法提取传送的内容
 * 通过DatagramSocket 的receive方法将接受到的数据存入上面定义的包中
 * 使用DatagramPacket的方法，提取数据。
 * 关闭资源。
 * Created by Administrator on 2017/3/10 0010.
 */
public class UDPReceive {
    public static void main(String[] args) throws IOException {
        //客户端在3000端口等待接受服务器发来的信息
        DatagramSocket ds = new DatagramSocket(3000);

        //开辟1024空间
        byte[] buf = new byte[1024];
        //定义一个接收包
        DatagramPacket pack = new DatagramPacket(buf, buf.length);

        //将从ds接收到的内容封装到pack包中
        ds.receive(pack);

        //利用getData()方法取出内容
        System.out.println("接收到的内容是:" + new String(pack.getData()));

        //关闭资源
        ds.close();
    }
}
