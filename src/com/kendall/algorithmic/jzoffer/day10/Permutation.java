package com.kendall.algorithmic.jzoffer.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @description:输入一个字符串,按字典序打印出该字符串中字符的所有排列。 例如输入字符串abc, 则打印出由字符a, b, c所能排列出来的所有字符串abc, acb, bac, bca, cab和cba。
 * 输入描述：输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 * 1
 * abc
 * abc acb bac bca cab cba
 * @author: kendall
 * @since: 2018/12/4
 */
public class Permutation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < groupNum; i++) {
            String str = scanner.next();
            strs.add(str);
        }

        for (String str : strs) {
            List<String> res = fun(str);
            res.forEach(System.out::println);
        }
    }

    /**
     * 本题可以用回溯算法
     *
     * abc
     * 1。首字符和0位的字符交换，对后面对字符进行排列，首字符和第0位交换复原成最初的位置
     * 2。首字符和第1位的字符交换，对后面的字符进行排列，首字符和第1位交换复原成最初的位置
     * 有n个字符，交换n次，对后面的字符排列n次，再复原n次
     * @param str
     * @return
     */
    private static List<String> fun(String str) {
        List<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }

        TreeSet<String> resSet = new TreeSet<>();
        permutate(str.toCharArray(), 0, resSet);

        res.addAll(resSet);
        return res;
    }

    /**
     * 排列，生产字符串，加入resSet
     * @param chars
     * @param index 与第index的字符交换
     * @param resSet
     */
    private static void permutate(char[] chars, int index, TreeSet<String> resSet) {
        if (index == chars.length - 1) {
            resSet.add(new String(chars));
            return;
        }

        //从index选取该位作为首字符进行交换，然后对后面对字符进行排列，再回溯
        //f(abc) = a+f(bc)  如1，abc= a+ bc-cb  2 交换ab b+ac-ca 然后交换ab复位回溯
        for (int i = index; i < chars.length; i++) {
            swap(chars, i, index);
            permutate(chars, index+1, resSet);
            swap(chars, i, index);
        }
    }

    private static void swap(char[] chars, int n, int m) {
        char tmp = chars[n];
        chars[n] = chars[m];
        chars[m] = tmp;
    }
}
