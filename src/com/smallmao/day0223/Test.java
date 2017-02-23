package com.smallmao.day0223;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**http://blog.csdn.net/zsw101259/article/details/7768908
 * Created by Administrator on 2017/2/23 0023.
 */
class Test
{
    public static void main(String[] args)throws IOException
    {
        ServerSocket server = new ServerSocket(44444);

        while(true)
        {
            Socket socket = server.accept();
            new Thread(new PicUpload(socket)).start();
        }

    }
}