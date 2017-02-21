package com.smallmao.day0221;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public class ExchangeValue {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入变量a的值:");
        int a = scan.nextInt();
        System.out.println("请输入变量b的值:");
        int b = scan.nextInt();

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
