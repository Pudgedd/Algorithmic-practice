package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
 * 如果没有则返回 -1（需要区分大小写）.
 * 1
 * asdwfaosdf
 * 3
 *
 * @author: kendall
 * @since: 2019/1/7
 */
public class FirstNotRepeatingChar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        for (int i = 0; i < groupNum; i++) {
            String str = scanner.next();
            System.out.println(fun(str));
        }
    }

    /**
     * 思路：使用map，将str的字母作key，出现次数未value，value最小的且对应key最先出现的位置即为返回结果
     * @param str
     * @return
     */
    private static int fun(String str) {
        if (str == null) {
            return -1;
        }
        if (str.length() == 1) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int[] map = new int[256];
        int res = -1;

        //asdwfaosdf
        for (int i = 0; i < chars.length; i++) {
            map[chars[i]] ++;
        }

        for (int i = 0; i < chars.length; i++) {
            if (map[chars[i]] == 1) {
                return i;
            }
        }

        return res;
    }
}
