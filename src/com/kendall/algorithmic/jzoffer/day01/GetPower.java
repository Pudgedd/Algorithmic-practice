package com.kendall.algorithmic.jzoffer.day01;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @description: 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方
 * @author: kendall
 * @since: 2018/9/24
 */
public class GetPower {
    /**
     * 2
     * 2 10
     * 2 3
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        ArrayList<Double>[] group = new ArrayList[t];
        for (int i = 0; i < t; i++) {
            group[i] = new ArrayList();
            group[i].add(scanner.nextDouble());
            group[i].add(scanner.nextDouble());
        }

        for (int i = 0; i < group.length; i++) {
            ArrayList<Double> arr = group[i];
            System.out.println(fun(arr.get(0), arr.get(1)));
        }

    }

    private static double fun(double base, double exponent) {
        double n = Math.abs(exponent);
        double res = getPower(base, n);
        return exponent > 0 ? res : 1 / res;
    }

    private static double getPower(double base, double exponent) {
        if (base == 0) {
            throw new RuntimeException("base can not be zero");
        }
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        if (exponent % 2 == 0) {
            double tmp = getPower(base, exponent / 2);
            tmp = tmp * tmp % 1000000007;
            return tmp;
        } else {
            double tmp = getPower(base, (exponent - 1) / 2);
            tmp = tmp * tmp % 1000000007;
            return tmp * base % 1000000007;
        }
    }


}
