package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:请实现一个函数用来匹配包括'.'和'*'的正则表达式。 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 * @author: kendall
 * @since: 2019/3/17
 */
public class Match {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str = scanner.next().toCharArray();
        char[] pattern = scanner.next().toCharArray();

        System.out.println(fun(str, pattern));

    }


    /**
     * 思路：看了答案。
     *
     * 当模式中的第二个字符不是“*”时：
     * 1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
     * 2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。
     *
     * 而当模式中的第二个字符是“*”时：
     * 1.如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。
     * 2.如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
     * a、模式后移2字符，相当于x*被忽略；
     * b、字符串后移1字符，模式后移2字符；
     * c、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；
     *
     * @param str
     * @param pattern
     * @return
     */
    private static boolean fun(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }
        if (str.length == 0 && pattern.length == 0) {
            return true;
        }
        return matchCore(str, 0, pattern, 0);
    }

    private static boolean matchCore(char[] str, int sIndex, char[] pattern, int pIndex) {
        //有效性检验：str到尾，pattern到尾，匹配成功
        if (sIndex == str.length && pIndex == pattern.length) {
            return true;
        }
        //pattern先到尾，此时匹配失败（可能最后两位是x*? 那么patIndex会等于pattern.length-1即pattern[patIndex]=*，而不会后移到越界）
        if (sIndex < str.length && pIndex == pattern.length) {
            return false;
        }

        if (pIndex + 1 != pattern.length && pattern[pIndex + 1] == '*') {
            //此处必须判断数组是否越界,即判断str是否到尾了，之前并没有判断str是否到尾。
            if (sIndex != str.length && str[sIndex] == pattern[pIndex] || sIndex != str.length && pattern[pIndex] == '.') {
                return matchCore(str, sIndex, pattern, pIndex + 2) ||
                        matchCore(str, sIndex+1, pattern, pIndex + 2) ||
                        matchCore(str, sIndex+1, pattern, pIndex);
            } else {
                return matchCore(str, sIndex+1, pattern, pIndex+2);
            }
        } else {
            //切记，不可以用sIndex++,会死循环！！
            if (sIndex != str.length && str[sIndex] == pattern[pIndex] || sIndex != str.length && pattern[pIndex] == '.') {
                return matchCore(str, sIndex+1, pattern, pIndex+1);
            } else {
                return false;
            }
        }
    }
}
