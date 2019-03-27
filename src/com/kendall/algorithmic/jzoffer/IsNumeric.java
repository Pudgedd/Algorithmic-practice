package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
 * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
 * @author: kendall
 * @since: 2019/3/19
 */
public class IsNumeric {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(fun(scanner.next().toCharArray()));
    }

    /**
     * 思路：不会，看了剑指 offer答案，但是感觉答案有问题，比如+123.a输入，输出也是true？
     *
     * @param str
     * @return
     */
    private static int index = 0;

    private static boolean fun(char[] str) {

        if (str.length < 1) {
            return false;
        }
        //扫描左边开始，是否有整数
        boolean flag = scanInteger(str);
        //是否是小数点
        if (index < str.length && str[index] == '.') {
            index++;
            flag = scanUnsignedInteger(str) || flag;
        }

        if (index < str.length && (str[index] == 'E' || str[index] == 'e')) {
            index++;
            flag = flag && scanInteger(str);
        }

        return flag && index == str.length;


    }

    /**
     * 从左到右遍历，扫描是否有integer
     * @param str
     * @return
     */
    private static boolean scanInteger(char[] str) {
        //有+或-，index加1
        if (index < str.length && (str[index] == '+' || str[index] == '-')) {
            index++;
        }
        return scanUnsignedInteger(str);
    }

    /**
     * 从左到右遍历，扫描是否有unsignedInteger
     * @param str
     * @return
     */
    private static boolean scanUnsignedInteger(char[] str) {
        int start = index;
        while (index < str.length && (str[index] > '0' || str[index] < '9')) {
            index++;
        }
        //是否存在整数
        return start < index;
    }
}
