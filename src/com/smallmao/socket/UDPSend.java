package com.smallmao.socket;

import java.io.IOException;
import java.net.*;

/**
 * 发送步骤：
 * 使用DatagramSocket(int port) 建立socket（套间字）服务。
 * 将数据打包到DatagramPacket中去
 * 通过socket服务发送 --send()方法
 * 关闭资源
 * Created by Administrator on 2017/3/10 0010.
 */
public class UDPSend {
    public static void main(String[] args) throws IOException {
        //
        DatagramSocket server = new DatagramSocket(9000);
        String data = "hello world!";
        DatagramPacket pack = new DatagramPacket(data.getBytes(), 0, data.length(), InetAddress.getLocalHost(), 3000);
        server.send(pack);
        server.close();
    }
}
