package com.smallmao.day0226;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全散列算法SHA(Secure Hash Algorithm)
 * http://tool.oschina.net/encrypt?type=2   在这里验证结果是否正确
 * Created by Administrator on 2017/2/26 0026.
 */
public class SHA1 {
    /**
     * 计算sha-1摘要
     * MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法。
     * 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值
     * @param message
     * @return
     */
    public static String getResult(String message){

        MessageDigest sha1 = null;
        try {
            sha1 = MessageDigest.getInstance("SHA-1");
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //使用指定的 byte 数组更新摘要
        sha1.update(message.getBytes());
        byte messageDigest[] = sha1.digest();
        System.out.println("第一个字节(十进制):" + messageDigest[0] +
                "  对应的十六进制:" + Integer.toHexString(messageDigest[0]));

        //字节数组转换为十六进制数
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i]&0xff);
            if (shaHex.length() < 2){
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String message = "ds4a6";
        String result =  SHA1.getResult(message);
        System.out.println(result);
    }
}
