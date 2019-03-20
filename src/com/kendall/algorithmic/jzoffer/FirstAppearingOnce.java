package com.kendall.algorithmic.jzoffer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @description:请实现一个函数用来找出字符流中第一个只出现一次的字符。 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * @author: kendall
 * @since: 2019/3/20
 */
public class FirstAppearingOnce {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] chars = scanner.next().toCharArray();
        for (char aChar : chars) {
            insert(aChar);
        }
        char firstAppearingOnce = getFirstAppearingOnce();
        System.out.println(firstAppearingOnce);
    }

    private static int[] map = new int[256];
    private static Queue<Character> queue = new LinkedList<>();

    /**
     * Insert one char from stringstream
     */
    public static void insert(char ch) {
        map[ch]++;
        if (map[ch] == 1) {
            queue.offer(ch);
        }
    }

    /**
     * return the first appearence once char in current stringstream
     */
    public static char getFirstAppearingOnce() {
        while (!queue.isEmpty() && map[queue.peek()] > 1) {
            queue.poll();
        }
        if (queue.isEmpty()) {
            return '#';
        }
        return queue.peek();
    }

}
