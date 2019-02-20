package com.kendall.algorithmic.jzoffer;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @description:汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 * 1
 * 3
 * abcXYZdef
 * ---
 * XYZdefabc
 *
 * @author: kendall
 * @since: 2019/2/20
 */
public class LeftRotateString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        for (int i = 0; i < groupNum; i++) {
            int n = scanner.nextInt();
            String str = scanner.next();
            System.out.println(fun1(str, n));
        }
    }

    /**
     * 思路：双向队列，全部入队后，左边出队，右边入队
     * @param str
     * @param n
     * @return
     */
    private static String fun(String str, int n) {
        if (str == null || str.length() < 2 || n < 1) {
            return str;
        }
        LinkedList<Character> queue = new LinkedList();
        char[] chars = str.toCharArray();
        for(char c : chars){
            queue.offer(c);
        }
        for(int i = 0; i < n; i++){
            char c = queue.poll();
            queue.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            sb.append(queue.poll());
        }
        return sb.toString();
    }

    /**
     * 思路1：时间复杂度O（n）。空间复杂度O（1）
     * @param str
     * @param n
     * @return
     */
    private static String fun1(String str, int n) {
        if (str == null || str.length() < 2 || n < 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        reverseStr(chars, 0, n-1);
        reverseStr(chars, n, str.length() - 1);
        reverseStr(chars, 0, str.length() - 1);
        return new String(chars);
    }

    private static void reverseStr(char[] chars, int start, int end) {
        char tmp;
        while (start < end) {
            tmp = chars[end];
            chars[end] = chars[start];
            chars[start] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 简单做法，O(n)
     * @param str
     * @param n
     * @return
     */
    private static String fun2(String str, int n) {
        if(str == null || str.length() == 0 || n < 1) {return str;}
        int len = str.length();
        str = str + str;
        n = n % len;
        len = n + len;
        return str.substring(n, len);
    }
}
