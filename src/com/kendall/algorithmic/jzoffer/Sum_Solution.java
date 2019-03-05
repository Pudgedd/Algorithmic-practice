package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description: 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * @author: kendall
 * @since: 2019/3/5
 */
public class Sum_Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fun(n));
    }

    /**
     * 思路：看了答案，递归。递归都有结束边界条件，有一个巧妙的思路是利用&&短路求值
     *
     * @param n
     * @return
     */
    private static int fun(int n) {
        int sum = n;
        boolean bool = ((n > 0) && (sum += fun(n - 1)) > 0);
        return sum;
    }
}
