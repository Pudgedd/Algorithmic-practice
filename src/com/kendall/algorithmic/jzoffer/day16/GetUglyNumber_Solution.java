package com.kendall.algorithmic.jzoffer.day16;

import java.util.Scanner;

/**
 * @description:把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。
 * 求按从小到大的顺序的第N个丑数。
 * 1
 * 3
 * 11
 * @author: kendall
 * @since: 2018/12/26
 */
public class GetUglyNumber_Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        for (int i = 0; i < groupNum; i++) {
            int n = scanner.nextInt();
            System.out.println(fun(n));
        }
    }

    /**
     * 做不出来，看了别人的答案。
     * 动态规划：思想是f(n)的值等于min（x*2,y*3,z*5）,x、y、z表示可以用于计算下一个丑数的最小丑数
     * 第一个为1，比如计算下一个丑数的时候
     * x,y,z等于未分别乘2，3，5的最小丑数，即都为1，min（1*2，1*3，1*5）=2
     * 这个时候1*2已经计算过一次了，所以表达式为min（2*2，1*3，1*5）=3
     * 这个时候1*2，1*3已经计算过一次了，所以表达式为min（2*2，2*3，1*5）=4
     * 这个时候1*2，1*3，2*2已经计算过一次了，所以表达式为min（3*2，2*3，1*5）=5
     * 这个时候1*2，1*3，2*2，1*5已经计算过一次了，所以表达式为min（3*2，2*3，2*5）=6，这个时候最小值为3*2或2*3，为了避免重复，都算计算过了
     * 这个时候1*2，1*3，2*2，1*5,3*2,2*3已经计算过一次了，所以表达式为min（4*2，3*3，2*5）=8
     * ...
     *
     * @param index
     * @return
     */
    private static int fun(int index) {
        if (index < 7) {
            return index;
        }

        int[] uglyNums = new int[index];
        uglyNums[0] = 1;

        //t2,t3,t5分表代表可以乘以2，3，5的（即尚未乘过2，3，5）的最小丑数的index
        int t2 = 0, t3 = 0, t5 = 0;
        for (int i = 1; i < index; i++) {
            uglyNums[i] = Math.min(uglyNums[t2] * 2, Math.min(uglyNums[t3] * 3, uglyNums[t5] * 5));
            if (uglyNums[i] == uglyNums[t2] * 2) t2++;
            if (uglyNums[i] == uglyNums[t3] * 3) t3++;
            if (uglyNums[i] == uglyNums[t5] * 5) t5++;
        }

        return uglyNums[index - 1];
    }
}
