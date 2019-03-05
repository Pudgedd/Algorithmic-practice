package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....
 * 这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * @author: kendall
 * @since: 2019/2/27
 */
public class LastRemaining_Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(fun(scanner.nextInt(),scanner.nextInt()));
    }

    /**
     * 思路：约瑟夫环，最后一个数字问题。可以用数学公式得出，也可以用链表或数组模拟链表环直接求出。
     * 用数组要注意边界条件。
     * @param n
     * @param m
     * @return
     */
    private static int fun(int n, int m) {
        if (n == 0 || m == 0) {
            return -1;
        }
        if (n == 1) {
            return 0;
        }

        int[] kids = new int[n];
        int count = 0;
        int pos = 0;
        int step = 1;
        while (count < n) {
            pos++;
            if (pos > n - 1) {
                pos = 0;
            }
            if (kids[pos] == -1) {
                continue;
            }
            step++;
            if (step == m) {
                kids[pos] = -1;
                count++;
                step = 0;
            }
        }
        return pos;
    }
}
