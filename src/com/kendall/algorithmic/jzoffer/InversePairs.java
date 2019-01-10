package com.kendall.algorithmic.jzoffer;

import java.util.Scanner;

/**
 * @description:在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。 输入一个数组, 求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 * 输入描述:
 * 题目保证输入的数组中没有的相同的数字
 * <p>
 * 数据范围：
 * <p>
 * 对于%50的数据,size<=10^4
 * <p>
 * 对于%75的数据,size<=10^5
 * <p>
 * 对于%100的数据,size<=2*10^5
 * 1
 * 8
 * 1 2 3 4 5 6 7 0
 * 7
 * @author: kendall
 * @since: 2019/1/8
 */
public class InversePairs {
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
            System.out.println(fun(arrs[i]));
        }
    }

    /**
     * 思路：采用归并排序的思路，现将
     *
     * @param array
     * @return
     */
    private static int fun(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int[] tmp = new int[array.length];
        int count = inversePairsCore(array, tmp, 0, array.length - 1);

        return count;
    }

    /**
     * 归并排序的改进，把数据分成前后两个数组(递归分到每个数组仅有一个数据项)，
     * 合并数组，合并时，出现前面的数组值array[i]大于后面数组值array[j]时；则前面
     * 数组array[i]~array[mid]都是大于array[j]的，count += mid - leftIndex + 1;
     * 比如 7 5 6 4
     * 拆分->7 5, 6 4
     * 拆分-> 7, 5, 6, 4
     * merge-> 7>5  ,count +1 = 1 ，已比较的要排序，防止再次比较。此时数组为 5 7， 6， 4
     * merge-> 6>4  ,count +1 = 2，已比较的要排序，防止再次比较。此时数组为 5 7， 4，6
     * merge-> 5>4  ,因为数组已排序，所以5后面的数也肯定大于4，count +2 = 4
     * 此时这一步merge还没结束，4放入临时数组，右边指针指向6，5<6因此不构成逆序。5放入临时数组，左边指针指向7
     * 7>6,所以7后面的都大于6， count+1 = 5;
     *
     * @param array
     * @param tmp      之所以将tmp通过参数传入，是为了避免递归时候new数组，消耗内存和时间
     * @param left
     * @param rightEnd
     * @return
     */
    private static int inversePairsCore(int[] array, int[] tmp, int left, int rightEnd) {
        if (left == rightEnd) {
            return 0;
        }
        // partition
        int mid = (left + rightEnd) >> 1;
        int leftCount = inversePairsCore(array, tmp, left, mid);
        int rightCount = inversePairsCore(array, tmp, mid + 1, rightEnd);

        // merge
        int count = 0;
        int leftIndex = left;
        int rightIndex = mid + 1;
        //tmpIndex 必须初始化为0
        int tmpIndex = 0;
        while (leftIndex <= mid && rightIndex <= rightEnd) {
            if (array[leftIndex] > array[rightIndex]) {
                // 之前写成了这个：count += rightIndex - mid; 结果少了很多
                // 原因是当leftIndex大于rightIndex的时候，leftIndex到mid的所有数字都大于rightIndex，即mid-leftIndex+1
                count += mid - leftIndex + 1;
                //此处必须mod，或采用long
                if (count > 1000000007) {
                    count %= 1000000007;
                }
                tmp[tmpIndex++] = array[rightIndex++];
            } else {
                tmp[tmpIndex++] = array[leftIndex++];
            }
        }

        while (leftIndex <= mid) {
            tmp[tmpIndex++] = array[leftIndex++];
        }
        while (rightIndex <= rightEnd) {
            tmp[tmpIndex++] = array[rightIndex++];
        }

        //将已排序的数组从tmp copy回array，这样在返回上层递归时就不会重复计算逆序对
        //其实分割的时候是通过left、mid、rightEnd来表示的，因此只需要把array中从left到rightEnd的数组复制回来即可
        for (int i = left; i <= rightEnd; i++) {
            array[i] = tmp[i - left];
        }

        return (count + leftCount + rightCount) % 1000000007;
    }

    /**
     * 思路：暴力比较，超时了。
     *
     * @param array
     * @return
     */
    private static int fun1(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int count = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    count++;
                }

            }
        }
        return count % 1000000007;
    }
}
