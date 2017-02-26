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
        System.out.println("32位操作系统中byte(8位)以32格式存放的,当数字为正数时,前25位为0,负数前25位为1");
        System.out.println("第一个字节(十进制):" + messageDigest[3] +
                "  对应的十六进制:" + Integer.toHexString(messageDigest[3]));

        //字节数组转换为十六进制数
        StringBuffer hexString = new StringBuffer();
        /*
         Integer.toHexString()以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。
         负数第一位为1,无符号整数值为参数加上 2^32

          在32位的电脑中数字都是以32格式存放的，如果是一个byte(8位)类型的数字，高24位里面都是随机数字，低8位 才是实际的数据。
           Java.lang.Integer.toHexString() 方法的参数是int(32位)类型，如果输入一个byte(8位)类型的数字，
           这个方法会把这个数字的高24为也看作有效位，这就必然导致错误，使用&0XFF操作，可以把高24位置0以避免这样错误 的发生。
         */
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
