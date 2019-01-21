package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 * 1
 * 8
 * 1 1 2 2 3 3 4 5
 * @author: kendall
 * @since: 2019/1/20
 */
public class FindNumsAppearOnce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        for (int i = 0; i < groupNum; i++) {
            int[] array = new int[scanner.nextInt()];
            for (int j = 0; j < array.length; j++) {
                array[j] = scanner.nextInt();
            }
            fun(array);
        }
    }

    /**
     * 思路：位运算（&与，|或，^异或，～取反，<<,>>,>>>)
     * a^a = 0; a^a^b = b;所以把数组中的数字异或操作，最后res=x^y;
     * 因为有两个数不相同，所以res最后的结果不可能为0，总有一个位位1
     * 找到res中某一位为1，那么可以根据这个位是否为1，将数组分为两个数组，x和y分别在两个数组中，
     * 其他相同数字则成对分布在某一个数组。^操作后就可以找到x和y
     *
     * @param array
     */
    private static void fun(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int x = 0,y = 0;

        int res = 0;
        for (int i : array) {
            res ^= i;
        }

        int index = getFirstBitOne(res);

        for (int i : array) {
            if (((i >> index) & 1) == 0) {
                x ^= i;
            }
        }
        y = res ^x;
        System.out.println(x);
        System.out.println(y);
    }

    /**
     * 找到数字中从又到左第一个1出现的index
     *
     * @param num
     * @return
     */
    private static int getFirstBitOne(int num) {
        int index = 0;
        //1 其实就是000....0001，num&1==0其实就是看最后一位是不是0
        while ((num & 1) == 0 && index <= 32) {
            num = num >> 1;
            index++;
        }
        return index;
    }
}
