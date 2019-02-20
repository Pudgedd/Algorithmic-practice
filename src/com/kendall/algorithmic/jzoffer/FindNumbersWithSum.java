package com.kendall.algorithmic.jzoffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description:输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * 1
 * 6 5
 * 1 2 3 4 5 6
 * ---
 * 1 4
 * @author: kendall
 * @since: 2019/2/20
 */
public class FindNumbersWithSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        for (int i = 0; i < groupNum; i++) {
            int[] array = new int[scanner.nextInt()];
            int sum = scanner.nextInt();
            for (int j = 0; j < array.length; j++) {
                array[j] = scanner.nextInt();
            }
            System.out.println(fun(array, sum));
        }

    }

    /**
     * 思路：假设数组中有多个组合的和为sum，那么最小乘积肯定为差最大的两个数
     * 因此使用双指针法，左右两个指针，若和大于sum，右指针左移，若和小于sum，左指针右移，直到相等即返回
     * @param array
     * @param sum
     */
    private static List<Integer> fun(int[] array, int sum) {
        List<Integer> res = new ArrayList<>(2);
        if (array == null || array.length < 2) {
            return res;
        }

        int pLow = 0;
        int pHigh = array.length-1;
        while (pLow < pHigh) {
            if (array[pLow] + array[pHigh] == sum) {
                res.add(array[pLow]);
                res.add(array[pHigh]);
                return res;
            } else if (array[pLow] + array[pHigh] > sum) {
                pHigh--;
            } else {
                pLow++;
            }
        }

        return res;
    }
}
