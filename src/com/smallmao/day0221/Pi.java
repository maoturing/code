package com.smallmao.day0221;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

/**
 * 使用蒙特卡洛算法求pi的值,假定有一个半径为r的圆及外切正方形,圆面积与正方形面积之比:(pi*r^2)/(4r^2) = pi/4;<br/>
 * 假设某人随机向正方形掷total个点,落在圆内的个数为count,由概率论可知,count/total = (pi*r^2)/(4r^2) = pi/4
 */
public class Pi {
    public static void main(String[] args) {
        double pi = getPi();
        System.out.println("根据蒙特卡洛算法得到pi = " + pi);
    }

    public static double getPi() {
        int total = 100000000;     //投掷次数
        int count = 0;          //投中的次数
        double x, y;
        for (int i = 0; i < total; i++) {
            x = Math.random();
            y = Math.random();
            if (x * x + y * y <= 1) {
                count++;
            }
        }
        double pi = count * 1.0 / total * 4;
        return pi;

    }
}
