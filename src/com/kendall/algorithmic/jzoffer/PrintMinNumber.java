package com.kendall.algorithmic.jzoffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 * 1
 * 3
 * 3 32 321
 * 321323
 *
 * @description:
 * @author: kendall
 * @since: 2018/12/22 今天是冬至哦～
 */
public class PrintMinNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        int[][] arrs = new int[groupNum][];
        for (int i = 0; i < groupNum; i++) {
            int n = scanner.nextInt();
            arrs[i] = new int[n];
            for (int j = 0; j < n; j++) {
                arrs[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < groupNum; i++) {
            System.out.println(fun2(arrs[i]));
        }
    }

    /**
     * 另一种思路：其实就是全排列的思想。找出数组的全排列，按照字典序返回最小的字符串即可
     *
     * @param numbers
     * @return
     */
    private static String fun2(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        if (numbers.length == 1) {
            return String.valueOf(numbers[0]);
        }

        String[] arrays = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arrays[i] = String.valueOf(numbers[i]);
        }
        List<String> numStrs = new ArrayList<>();

        permutate(arrays, 0, numStrs);

        String res = numStrs.get(0);
        for (int i = 1; i < numStrs.size(); i++) {
            if (res.compareTo(numStrs.get(i)) > 0) {
                res = numStrs.get(i);
            }
        }

        return res;
    }

    /**
     * 回溯法实现全排列，即f(abc) = a+f(bc) + b+(ac) + c+f(ab)
     *
     * @param arrays
     * @param startIndex
     * @param set
     */
    private static void permutate(String[] arrays, int startIndex, List<String> set) {
        if (startIndex == arrays.length - 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrays.length; i++) {
                sb.append(String.valueOf(arrays[i]));
            }
            set.add(sb.toString());
        }

        for (int i = startIndex; i < arrays.length; i++) {
            swap(arrays, i, startIndex);
            permutate(arrays, startIndex + 1, set);
            swap(arrays, i, startIndex);
        }
    }

    private static void swap(String[] strings, int n, int m) {
        String tmp = strings[n];
        strings[n] = strings[m];
        strings[m] = tmp;
    }

    /**
     * 思路：因为数字不允许拆分，所以其实比较的就是拼接后的字典序。
     * 即：若a+b < b+a 则a在b之前
     * 实现：用java的sort方法，实现comparator接口，按照上述的规则排序后，拼接即可。
     *
     * @param numbers
     * @return
     */
    private static String fun(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        if (numbers.length == 1) {
            return String.valueOf(numbers[0]);
        }

        List<String> numStrs = new ArrayList<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            numStrs.add(String.valueOf(numbers[i]));
        }

        Collections.sort(numStrs, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));

        StringBuilder sb = new StringBuilder();
        for (String numStr : numStrs) {
            sb.append(numStr);
        }

        return sb.toString();
    }
}
