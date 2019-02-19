package com.kendall.algorithmic.jzoffer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @description: 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 * 1
 * 100
 * @author: kendall
 * @since: 2019/1/22
 */
public class FindContinuousSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        int[] arr = new int[groupNum];
        for (int i = 0; i < groupNum; i++) {
            arr[i] = scanner.nextInt();
        }

        for (int num : arr) {
            ArrayList<ArrayList<Integer>> res = fun(num);
            res.stream().flatMap(subList -> subList.stream()).map(x-> x+",").forEach(System.out::print);
        }
    }

    /**
     * 思路：滑动窗口思路，两个指针，leftNum和rightNum
     * 计算从left到right的和：(a0+an)*n/2
     * 等于sum，则记为一个结果序列，再将窗口的右（或左）指针右移一位
     * 小于sum，右指针右移一位
     * 大于sum，左指针右移一位
     *
     * 直到左指针大于等于（sum+1）/2结束
     *
     * @param sum
     * @return
     */
    private static ArrayList<ArrayList<Integer>> fun(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3) {
            return res;
        }

        int endNum = (sum+1) / 2;
        int leftNum = 1;
        int rightNum = 2;
        while (leftNum < endNum) {
            int curSum = (leftNum + rightNum) * (rightNum - leftNum + 1) / 2;
            if (curSum == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = leftNum; i <= rightNum; i++) {
                    list.add(i);
                }
                res.add(list);
                rightNum++;
            } else if (curSum < sum) {
                rightNum++;
            } else {
                leftNum++;
            }
        }
        return res;
    }
}
