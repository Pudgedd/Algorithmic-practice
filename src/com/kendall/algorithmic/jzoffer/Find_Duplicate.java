package com.kendall.algorithmic.jzoffer;

/**
 * @description:在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 *
 * @author: kendall
 * @since: 2019/3/7
 */
public class Find_Duplicate {
    public static void main(String[] args) {
        int[] numbers = new int[]{2,3,1,0,2,5,3};
        int[] res = new int[1];
        fun(numbers, numbers.length, res);
        System.out.println(res[0]);
    }

    /**
     * 思路：用数组实现map，遍历numbers，遍历到某数，数组对应下标value加1，若大于2则返回
     * @param numbers
     * @param length
     * @param duplication
     */
    private static boolean fun(int numbers[],int length,int [] duplication) {
        if (numbers == null || length < 2) {
            return false;
        }

        int[] map = new int[length];
        duplication[0] = -1;

        for (int number : numbers) {
            map[number]++;
            if (map[number] > 1) {
                duplication[0] = number;
                return true;
            }
        }
        return false;
    }
}
