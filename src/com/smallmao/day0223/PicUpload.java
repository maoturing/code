package com.smallmao.day0223;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 */
public class PicUpload implements Runnable {
    private Socket socket;

    public PicUpload(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            //把上传的图片放到统一的目录下
            File dir = new File("pictures");
            if(!dir.exists())
                dir.mkdir();

            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip+"连接到客户端");

            int num = 0;
            File file = new File(dir,ip+(++num)+".jpg");

            while(file.exists())
            {
                file = new File(dir,ip+(++num)+".jpg");
            }

            FileOutputStream fos = new FileOutputStream(file);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            byte[] arr = new byte[1024];
            int len = 0;
            while((len = in.read(arr))!=-1)
            {
                fos.write(arr,0,len);
            }
            fos.close();

            out.write("上传成功".getBytes());
            socket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
