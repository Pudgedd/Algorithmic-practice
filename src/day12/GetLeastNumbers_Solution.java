package day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * 1
 * 8 4
 * 4 5 1 6 2 7 3 8
 * 1 2 3 4
 *
 * @description:
 * @author: kendall
 * @since: 2018/12/16
 */
public class GetLeastNumbers_Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int groupNum = scanner.nextInt();
        List[] arrs = new ArrayList[groupNum];
        int[] arrOfK = new int[groupNum];
        for (int i = 0; i < groupNum; i++) {
            int arrSize = scanner.nextInt();
            arrs[i] = new ArrayList<Integer>(arrSize);
            arrOfK[i] = scanner.nextInt();

            for (int j = 0; j < arrSize; j++) {
                arrs[i].add(scanner.nextInt());
            }
        }

        for (int i = 0; i < groupNum; i++) {
            List<Integer> res = fun(arrs[i], arrOfK[i]);
            res.forEach(System.out::print);
        }
    }

    /**
     * 典型的top k问题
     * 第一反应是用堆来实现。
     * 维护一个大根堆，遍历数组，只要比当前堆root小的，即把当前数变为root，然后调整堆
     *
     * @param list
     * @param k
     * @return
     */
    private static List<Integer> fun(List<Integer> list, int k) {
        List<Integer> res = new ArrayList<>();
        if (list == null || list.size() == 0 || k < 1) {
            return res;
        }
        Integer[] arr = list.stream().toArray(Integer[]::new);

        for (int parent = (k - 1) / 2; parent >= 0; parent--) {
            adjustMaxHeap(arr, parent, k);
        }

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arr[0]) {
                swap(arr, i, 0);
                adjustMaxHeap(arr, 0, k);
            }
        }

        for (int i = 0; i < k; i++) {
            res.add(arr[i]);

        }
        return res;
    }

    private static void swap(Integer[] arr, int n, int m) {
        int tmp = arr[n];
        arr[n] = arr[m];
        arr[m] = tmp;
    }

    private static void adjustMaxHeap(Integer[] maxHeap, int index, int len) {
        int parent = index;
        int tmp = maxHeap[parent];
        for (int child = parent * 2 + 1; child < len; child = 2 * child + 1) {
            if (child + 1 != len && maxHeap[child + 1] > maxHeap[child]) {
                child++;
            }
            if (tmp >= maxHeap[child]) {
                break;
            } else {
                maxHeap[parent] = maxHeap[child];
                parent = child;
            }
        }
        maxHeap[parent] = tmp;
    }
}
