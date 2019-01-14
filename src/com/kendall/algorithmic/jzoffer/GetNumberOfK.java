package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description: 统计一个数字在排序数组中出现的次数。
 * 1
 * 4 7
 * 1 2 4 4 4 6 7
 * 3
 * @author: kendall
 * @since: 2019/1/14
 */
public class GetNumberOfK {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupSize = scanner.nextInt();
        int[][] arrays = new int[groupSize][];
        for (int i = 0; i < groupSize; i++) {
            int k = scanner.nextInt();
            int len = scanner.nextInt();
            arrays[i] = new int[len];
            for (int j = 0; j < len; j++) {
                arrays[i][j] = scanner.nextInt();
            }
            System.out.println(fun(arrays[i], k));
        }
    }

    /**
     * 思路：其实就是在有序数组中寻找一个数，和二分查找问题一样，。O(logN)
     * @param array
     * @param k
     * @return
     */
    private static int fun(int[] array, int k) {
        if (array == null || array.length == 0 || array[0] > k) {
            return 0;
        }

        int res = 0;
        int firstK = getFirstK(array, k, 0, array.length - 1);
        int lastK = getLastK(array, k, 0, array.length - 1);

        if (firstK > -1 && lastK > -1) {
            res = lastK - firstK + 1;
        }
        return res;
    }

    private static int getLastK(int[] array, int k, int start, int end) {

        int mid = (start + end) >> 1;
        while (start <= end) {
            if (array[mid] > k) {
                end = mid - 1;
            } else if (array[mid] < k) {
                start = mid + 1;
            } else if (mid + 1 < array.length && array[mid + 1] == k) {
                start = mid+1;
            } else {
                return mid;
            }
            mid = (start + end) >> 1;
        }

        return -1;
    }

    private static int getFirstK(int[] array, int k, int start, int end) {

        if (start > end) {
            return -1;
        }

        int mid = (start + end) >> 1;
        if (array[mid] > k) {
           return getFirstK(array, k, start, mid - 1);
        } else if (array[mid] < k) {
            return getFirstK(array, k, mid + 1, end);
        } else if (mid - 1 >= 0 && array[mid - 1] == k) {
            return getFirstK(array, k, start, mid - 1);
        } else {
            return mid;
        }
    }

    /**
     * 思路：遍历数组，找到数组中等于k的位置，然后统计出现次数。时间复杂度O(n)
     * @param array
     * @param k
     * @return
     */
    private static int fun1(int[] array, int k) {
        if (array == null || array.length == 0 || array[0] > k) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                res++;
            }
        }
        return res;
    }
}
